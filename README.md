# fastlms
✅ 회원 로그인시 로그인 히스토리(로그) 기능
- spring security -> UserAuthenticationSuccessHandler -> LoginHistoryService -> LoginHistoryRepository

✅ 관리자 회원 상세 정보에 로그인 목록 보기 기능
- AdminMemberController -> (Get)detail() -> LoginHistoryService

✅ 배너관리(백오피스 기능)
- banner(package) Admincontroller -> service -> repository

✅ 프론트 배너 노출 기능
- MainController -> BannerService -> FrontBannerOutput( usingYn check, sort , target(click) data parsing ) -> index.html
