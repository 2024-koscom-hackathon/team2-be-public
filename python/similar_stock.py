import pandas as pd
import numpy as np
import pandas as pd

import FinanceDataReader as fdr
from mpl_finance import candlestick_ohlc

import matplotlib.pyplot as plt
import matplotlib.gridspec as gridspec




def chartSet():
    # 차트의 기본 캔버스 크기 설정
    import matplotlib.pyplot as plt

    plt.rcParams["figure.figsize"] = (10, 8)
    # 차트의 기본 라인 굵기 설정
    plt.rcParams['lines.linewidth'] = 1.5
    # 차트의 기본 라인 컬러 설정
    plt.rcParams['lines.color'] = 'tomato'
    #  차트 설정
    import matplotlib.pyplot as plt

    plt.rcParams["font.family"] = 'nanummyeongjo'
    plt.rcParams["figure.figsize"] = (14,4)
    plt.rcParams['lines.linewidth'] = 2
    plt.rcParams["axes.grid"] = True

# start~end 주가데이터 가져오기
def day(startdate,enddate,data):
    # 주가 정보의 시작: 종료 추출
    data_ = data.loc[startdate:enddate]
    return data_

# 거래량 차트 그리기
def volume_chart(data_):
    fig = plt.figure()
    fig.set_facecolor('w')
    # 2개의 캔버스 생성 후 1번째는 차트를 2번째는 거래량
    gs = gridspec.GridSpec(2, 1, height_ratios=[3, 1])

    axes = []
    axes.append(plt.subplot(gs[0]))
    axes.append(plt.subplot(gs[1], sharex=axes[0]))
    axes[0].get_xaxis().set_visible(False)

    x = np.arange(len(data_.index))
    ohlc = data_[['Open', 'High', 'Low', 'Close']].values
    dohlc = np.hstack((np.reshape(x, (-1, 1)), ohlc))

    # 봉차트
    candlestick_ohlc(axes[0], dohlc, width=0.5, colorup='r', colordown='b')

    # 거래량 차트
    axes[1].bar(x, data_['Volume'], color='grey', width=0.6, align='center')
    axes[1].set_xticks(range(len(x)))
    axes[1].set_xticklabels(list(data_.index.strftime('%Y-%m-%d')), rotation=90)
    axes[1].get_yaxis().set_visible(False)

    plt.tight_layout()


# 종가 데이터만 추출 후 정규화(Normalization)
def normalization(data_):
    # 종가(Close)만 추출
    close = data_['Close']
    close
    base =(close-close.min())/(close.max()-close.min())
    return base 


# cosine_similarity 구하기    
def cosine_similarity(x, y):
    # 윈도우 사이즈
    

    return np.dot(x, y) / (np.sqrt(np.dot(x, x)) * np.sqrt(np.dot(y, y)))


# 윈도우 범위(window_size) 만큼의 유사 패턴 찾기 (cosine 유사도)
def calc_cosine_similarity(base,data):
    # 윈도우 사이즈
    window_size = len(base)
    # 검색 횟수
    moving_cnt = len(data) - window_size - 1
    # 코사인 유사도를 계산하여 저장해줄 리스트를 생성합니다
    sim_list = []

    for i in range(moving_cnt):
        # i 번째 인덱스 부터 i+window_size 만큼의 범위를 가져와 target 변수에 대입합니다
        target = data['Close'].iloc[i:i+window_size]
        
        # base와 마찬가지로 정규화를 적용하여 스케일을 맞춰 줍니다
        target = (target - target.min()) / (target.max() - target.min())
        
        # 코사인 유사도를 계산합니다
        cos_similarity = cosine_similarity(base, target)
        
        # 계산된 코사인 유사도를 추가합니다
        sim_list.append(cos_similarity)
    return sim_list
    
def drawing_similarity(sim_list):
    return pd.Series(sim_list).sort_values(ascending=False).head(20)

def drawing(data_,idx,window_size):

    # target 변수에 종가 데이터의 [기준 인덱스] 부터 [기준 인덱스 + window_size + 예측(5일)] 데이터를 추출합니다
    target = data['Close'].iloc[idx:idx+window_size]

    # 정규화를 적용합니다
#    target = (target - target.min()) / (target.max() - target.min())

    # 결과를 시각화합니다
    #plt.plot(data_.values, label='base', color='grey')
    plt.plot(target.values, label='target', color='orangered')
#    plt.plot(data_.values, label='data_', color='orangered')
#    plt.xticks(np.arange(len(data_)), list(data_.index.strftime('%Y-%m-%d')), rotation=45)
    
    plt.xticks(np.arange(len(target)), list(target.index.strftime('%Y-%m-%d')), rotation=45)
    # plt.axvline(x=len(base)-1, c='grey', linestyle='--')
    # plt.axvspan(len(base.values)-1, len(target.values)-1, facecolor='ivory', alpha=0.7)
    plt.legend()
    plt.show()

if __name__ == "__main__":
    
    target = '삼성전자'
    start = '20240123'
    end = ', '

    symbols = pd.read_csv('./krx_stock_symbols.csv')
    code = symbols[symbols['Name'] == target]['Code'].iloc[0]

    # stock_data = fdr.DataReader(code, start, end)
    data = fdr.DataReader(code)
    #chartSet()
    # start~end 종가 data 추출
    data_ = day(start,end,data)
    
    # 종가 data normalization
    base = normalization(data_)
    sim_list = calc_cosine_similarity(base,data)
    pd=drawing_similarity(sim_list)
    index_lst = (pd.iloc[1:20].index)
    print(index_lst)
    window_size = len(base)
    print(f'window_size:{window_size}')
    #drawing(index_lst[0],window_size)
    idx= index_lst
    target = data['Close'].iloc[idx].index
    print(target)
#    print(data['Close'])
#    print(idx)
    
    date_lst = []

    for i in enumerate(zip(target,index_lst)):
        print(i[0],i[1])
        n=i[0]
        date=i[1][0]
        idx=i[1][1]
        if(len(date_lst)>=7):
            break
        #print(str(date)[:10])
        year = int(str(date)[:4])
        if(year>=2010):
            date_lst.append([n,idx,str(date)[:10]])
    # print(date_lst)
    
    # for idx,date in enumerate(target):
    #     if(len(date_lst)>=7):
    #         break
    #     #print(str(date)[:10])
    #     year = int(str(date)[:4])
    #     if(year>=2010):
    #         date_lst.append([idx,str(date)[:10]])
    
    print(date_lst)
    
    # print()
    
    for _,idx,date in date_lst:
        drawing(data_,idx,window_size)
    
    