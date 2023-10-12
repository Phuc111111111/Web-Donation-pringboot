1. Mô tả dự án

Dự án quyên góp từ thiện sẽ đưa lên thông tin những hoàn cảnh khó khăn, cần sự giúp đỡ của những nhà hảo tâm, thiện nguyện. 
Giúp bạn dễ dàng chung tay quyên góp tiền cùng hàng triệu người, giúp đỡ các hoàn cảnh khó khăn trên khắp cả nước. 
Ngoài ra, người quản lý (admin) có thể theo dõi được thông tin của các đợt quyên góp thông qua: trạng thái đợt quyên góp, 
số tiền của đợt quyên góp, thông tin cơ bản của những người tham gia quyên góp ,...

2 Chức năng hệ thống
Phần quyền người dùng gồm:
-Người quản lý hệ thống - Admin, Người dùng chức năng hệ thống - User, 
-admin phải đăng nhập tài khoản mật khẩu để truy cập vào hệ thống - quản lý
-user phải đăng nhập mới có thể đóng góp tiền
3 Chức năng user gồm:
- đăng nhập , đăng xuất
- ủng hộ tiền,
- xem thông tin về các đợt quyên góp,
- phân trang
4 Chức Năng của ADMIN gồm :
-quản lý danh sách user - CRUD-user - tìm kiếm, xắp xếp, phân trang- khóa tài khoản
-quản lý các đợt quyên góp - CRUD-Quyên góp - tìm kiếm, xắp xếp, thiết lập trạng thái cho các đợt quyên góp (4 status)
-quản lý các người dùng quyên góp - phần trang, xăp xếp, xác nhận tiền từ người dùng,
Một đợt quyên góp sẽ được diễn ra thông qua 4 trạng thái cơ bản chính:
Chi tiết về trang thái quyên góp của quản lý đợt quyên góp
-Trạng thái mới tạo: Đợt quyên góp vừa mới được khởi tạo chưa bắt đầu chạy quyên góp. Người dùng hệ thống sẽ khởi tạo đợt quyên góp.
-Đang quyên góp: Đợt quyên góp đang được quyên góp. Người dùng hệ thống có thể tham gia đợt quyên góp này.
-Kết thúc đợt quyên góp: Đợt quyên góp hoàn thành mục tiêu, có thể là về thời gian hoặc số tiền cần quyên góp. 
-Đóng quyên góp: Đóng đợt quyên góp, không ai có thể tham gia quyên góp được nữa và trạng thái của đợt quyên góp sẽ KHÔNG thể thay đổi được.
-người quản trị hệ thống (Admin) mới có quyền thay đổi trạng thái của đợt quyên góp, có thể thay đổi các trạng thái luân phiên nhau NHƯNG ở trạng thái ‘Đóng quyên góp’ sẽ không quay trở lại trạng thái ‘Kết thúc đợt quyên góp’ hay ‘Đang quyên góp’ được cả.

Sử dụng spring boot: spring security, themleaf, data-JPA, SQL server,
