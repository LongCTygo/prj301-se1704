package dao;

import connecttest.DAOEntityAbstract;
import connecttest.DBConnect;
import display.ProductDisplay;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProduct extends DAOEntityAbstract<Product> {

    @Deprecated
    public int addProduct(Product product) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([pid]\n"
                + "           ,[pname]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[cateID])\n"
                + "     VALUES('" + product.getPid() + "','" + product.getPname()
                + "','" + product.getQuantity() + "','" + product.getPrice()
                + "','" + product.getImage() + "','" + product.getDescription()
                + "','" + product.getStatus() + "','" + product.getCateID() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    @Override
    public int add(Product product) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([pid]\n"
                + "           ,[pname]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[cateID])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, product.getPid());
            pre.setString(2, product.getPname());
            pre.setInt(3, product.getQuantity());
            pre.setDouble(4, product.getPrice());
            pre.setString(5, product.getImage());
            pre.setString(6, product.getDescription());
            pre.setInt(7, product.getStatus());
            pre.setInt(8, product.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    @Override
    public int update(Product product) {
        int n = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [pname] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[cateID] = ?\n"
                + " WHERE [pid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, product.getPname());
            pre.setInt(2, product.getQuantity());
            pre.setDouble(3, product.getPrice());
            pre.setString(4, product.getImage());
            pre.setString(5, product.getDescription());
            pre.setInt(6, product.getStatus());
            pre.setInt(7, product.getCateID());
            pre.setString(8, product.getPid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAll() {
        String sql = "select * from Customer";

        try {

            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                /* dataType varName = rs.getDataType("fieldName|index"); */
                String pid = rs.getString("pid");
                String Pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");
                Product pro = new Product(pid, Pname, quantity, price, image, description, status, cateId);
                System.out.println(pro.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Vector<Product> getAll(String sql) {
        Vector<Product> vector = new Vector<Product>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString("pid");
                String Pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");
                Product pro = new Product(pid, Pname, quantity, price, image, description, status, cateId);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    @Override
    public int remove(String id) {
        int n = 0;
        String sql = "delete from Product where pid ='" + id + "'";
        try {
            ResultSet rs = this.getData("Select * from BillDetail where pid='" + id);
            if (rs.next()) {
                n = -1;
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        int n = dao.remove("P01");
        if (n > 0) {
            System.out.println("Removed");
        }
    }

    public Vector<ProductDisplay> getDisplay(String sql) {
        Vector<ProductDisplay> vector = new Vector<>();
        try {
            ResultSet rs = this.getData(sql);
            while (rs.next()) {
                String pid = rs.getString("pid");
                String Pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String cate = rs.getString("cateName");
                int cid = rs.getInt("cateID");
                ProductDisplay pd = new ProductDisplay(pid, Pname, quantity, price, image, description, cate, status,cid);
                vector.add(pd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
