package app.BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import app.DAL.User_DAL;
import app.DTO.User_DTO;
import app.database.ConnectDatabase;

public class User_BLL {
	public User_DAL userDAL = new User_DAL();
	public User_DTO userDTO = new User_DTO(); 
	public ArrayList<User_DTO> users = new ArrayList<User_DTO>();
	
	public static User_DTO getInstance() {
        return new User_DTO();
    }
    
    public ArrayList<User_DTO> selectAll() {
        ArrayList<User_DTO> result = new ArrayList<User_DTO>();
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            String query = "SELECT * FROM users";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
            	int id = rs.getInt("userID");
                String username = rs.getString("userName");
                String email = rs.getNString("userEmail");
                String password = rs.getString("userPassword");
                String fullname = rs.getString("userFullName");
                int isAdmin = rs.getInt("isAdmin");
                User_DTO nv = new User_DTO(id,username, email, password, fullname, isAdmin);
                result.add(nv);
            }
            db.closeConnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public boolean create(User_DTO user) {    	
        if (userDAL.create(user) != 0) {
        	users.add(user);
            return true;
        }
        
        return false;
    }
    
//    public NhanVienDTO selectByAccountId(String t) {
//        NhanVienDTO nv = null;
//        try{
//            Connection con = DBConnector.getConnection();
//            String query = "SELECT * FROM taikhoan tk join nhanvien nv on tk.nhanVien_id=nv.id where tk.id=?";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1,t);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String ho = rs.getString("ho");
//                String ten = rs.getNString("ten");
//                String gioiTinh = rs.getString("gioitinh");
//                String sdt = rs.getString("soDienThoai");
//                String email = rs.getString("email");
//                int trangThai = rs.getInt("trangThai");
//                nv = new NhanVienDTO(id, ho, ten, gioiTinh, sdt, email, trangThai);
//            }
//            DBConnector.closeConnection(con);
//        } catch (Exception e) {
//            // TODO: handle exception  
//            System.out.println(e);
//        }
//        return nv;
//    }
//    
//    public int getAutoIncrement() {
//        int result = -1;
//        try {
//            Connection con = (Connection) DBConnector.getConnection();
//            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'nhanvien'";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery(sql);
//            if (!rs.isBeforeFirst()) {
//                System.out.println("No data");
//            } else {
//                while (rs.next()) {
//                    result = rs.getInt("AUTO_INCREMENT");
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return result;
//    }
//    
//    public int insert(NhanVienDTO nv) {
//        int result = 0;
//        try {
//            Connection conn = (Connection) DBConnector.getConnection();
//            String query = "INSERT INTO `nhanvien`(`ho`, `ten`, `gioiTinh`, `soDienThoai`, `email`) VALUES (?,?,?,?,?)";
//            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
//            pst.setString(1, nv.getHo());
//            pst.setString(2, nv.getTen());
//            pst.setString(3, nv.getGioiTinh());
//            pst.setString(4, nv.getSoDienThoai());
//            pst.setString(5, nv.getEmail());
//            result = pst.executeUpdate();
//            DBConnector.closeConnection(conn);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return result;
//    }
//    
//    public int update(NhanVienDTO nv) {
//        int result = 0;
//        try {
//            Connection conn = (Connection) DBConnector.getConnection();
//            String query = "UPDATE `nhanvien` SET `ho`=?,`ten`=?,`gioiTinh`=?,`soDienThoai`=?,`email`=? WHERE `id`=?";
//            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
//            pst.setString(1, nv.getHo());
//            pst.setString(2, nv.getTen());
//            pst.setString(3, nv.getGioiTinh());
//            pst.setString(4, nv.getSoDienThoai());
//            pst.setString(5, nv.getEmail());
//            pst.setInt(7, nv.getId());
//            result = pst.executeUpdate();
//            DBConnector.closeConnection(conn);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return result;
//    }
//    
//    public int delete(int id) {
//        int result = 0;
//        try {
//            Connection conn = (Connection) DBConnector.getConnection();
//            String query = "UPDATE `nhanvien` SET `trangThai`=0 WHERE `id`=?";
//            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
//            pst.setInt(1, id);
//            result = pst.executeUpdate();
//            DBConnector.closeConnection(conn);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return result;
//    }
}
