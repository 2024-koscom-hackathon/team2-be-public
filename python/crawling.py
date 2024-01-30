import openai
import requests
from bs4 import BeautifulSoup as bs
from fake_useragent import UserAgent
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By

driver = ""
Stock_item=""
start_date = ""
end_date = ""

title_text=[] #제목
link_text=[] #링크
source_text=[] #출처
date_text=[] #날짜
result={}


# 크롬드라이버 셋팅
def set_chrome_driver(headless=True):
    options = webdriver.ChromeOptions()
    if headless:
        options.add_argument('headless')
    options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
    return driver


#1
def geturl(Stock_item,start_date,end_date):
    # 요청하고자 하는 샘플 뉴스기사 URL
    global driver
    url = f'https://www.mk.co.kr/search?word={Stock_item}&sort=accuracy&dateType=direct&startDate={start_date}&endDate={end_date}&searchField=title'
    #url = f'https://www.mk.co.kr/search?word={Stock_item}&dateType=direct&startDate={start_date}&endDate={end_date}'
    # 크롬드라이버 셋팅
    # driver 설정
    driver = set_chrome_driver(False)

    # URL 요청
    driver.get(url)
    # 뉴스기사 모두 가져오기
    while True:
        try:
            driver.find_element(By.CLASS_NAME,'drop_sub_news_btn').click()
        except:
            break;

    crawling_page()
#2
def crawling_page():    
    from datetime import datetime

    global result
    # 전체 페이지의 HTML 가져오기
    html = driver.page_source

    # BeautifulSoup을 사용하여 HTML 파싱
    soup = bs(html, 'html.parser')


    news_nodes = soup.find_all('li', class_='news_node')
    for news_node in news_nodes:
        # a 태그의 href 가져오기
        link = news_node.find('a')['href']
        print(link)
        link_text.append(link)

    #날짜 추출
    date_list = soup.select('.info_group > .time_info')
    for date in date_list:
        if "시간" in date.text:
            date_text.append(datetime.today().strftime("%Y.%m.%d"))
        else:
            date_text.append(date.text[:10])

    # 제목 추출
    title_list=soup.find_all('h3',class_='news_ttl')
    for title in title_list:
        title_text.append(title.text)

    source = '매일경제'
    for _ in range(len(title_text)):
        source_text.append(source)    


    #모든 리스트 딕셔너리형태로 저장
    result= {"date" : date_text , "title":title_text ,  "source" : source_text ,"link":link_text}
    # 크롤링한 일자/제목/출처/링크
    print(result.keys())
    for i in range(len(result['date'])):
        print(result['date'][i],result['title'][i],result['source'][i],result['link'][i])
    
    content_crawling()
    
#3
def content_crawling():
    # 본문기사 크롤링
    global result
    sub_txt_list = []
    for i in range(len(result['link'])):
        print(i)
        sub_url = result['link'][i]
        print(sub_url)
        # 페이지 요청
        response = requests.get(sub_url)
        html = response.text
        
        # BeautifulSoup을 사용하여 HTML 파싱
        soup = bs(html, 'html.parser')
        
        sec_body = soup.find('div', class_='sec_body')
        if sec_body:
            news_cnt_detail_wrap = sec_body.find('div', class_='news_cnt_detail_wrap')
            if news_cnt_detail_wrap:
    #            print(news_cnt_detail_wrap.text)
                sub_txt_list.append(news_cnt_detail_wrap.text)

            else:
                print("div class='news_cnt_detail_wrap' not found within div class='sec_body'.")
        else:
            sub_txt_list.append('Err')
            print("div class='sec_body' not found on the page.")

    result["sub_txt"] = sub_txt_list

    chatgpt()

def chatgpt():
    global result
    client = openai.OpenAI(api_key = "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")
    summary_lst=[]
    # chatgpt 요약 
    for i in range(len(result['sub_txt'])):
        if "Err" in result["sub_txt"][i]:
                summary_lst.append("Error")
                print(f"{i}  --  err")
                continue
        else:
            try:
                response = client.chat.completions.create(
                model="gpt-3.5-turbo",
                messages=[
                    {"role": "system", "content": "너는 친절하게 답변해주는 비서야"},
                    {"role": "user", "content": f"다음 문장을 요약해줘. {result['sub_txt'][i]} , 그리고 해당 기사가 코스피 지수 및 {Stock_item} 종목 주가에 긍정적인지 부정적인지 판단해줘"}
                    ]
                )
                print(f"진행 상황 {i+1}/{len(result['sub_txt'])}" )
                print(response.choices[0].message.content)
                print("========")
                summary_lst.append(response.choices[0].message.content)
            except:
                summary_lst.append("Error")        

    make_df()
    
def make_df():
    for key in result.keys():
        print(key,len(result[key]))
    
    df = pd.DataFrame(result)  #df로 변환
    print(df)
    outputFileName = f'{Stock_item}_{end_date}_news.xlsx'
    RESULT_PATH ='./'  #결과 저장할 경로
    df.to_excel(RESULT_PATH+outputFileName,sheet_name='sheet1')



if __name__ == "__main__":
    Stock_item = input("종목 입력: ")
    #2024-01-01
    start_date = input("시작일자 ('ex. 2000-01-01 형식으로 넣어주세요.') : ")
    #2024-01-19
    end_date = input("종료일자 ('ex. 2000-01-01 형식으로 넣어주세요.') : ")
    geturl(Stock_item,start_date,end_date)
    
    
    