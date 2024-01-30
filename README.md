# team2-be
> 2024 해커톤 2조 BE

## Branch 전략
### develop (default)
개발 브랜치. feature별로 세부 브랜치 나누어 개발 진행 후 develop 브랜치에 merge
### main
서버 배포가 진행되는 브랜치. 개발 완료 후 main에 merge 되면 자동 배포(Docker + NCP + Github Actions)
