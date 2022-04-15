package ru.buba.demoex.manager;

import ru.buba.demoex.Main;
import ru.buba.demoex.entity.ProductEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    // insert, selectAll, selectById, update, deletex2

    public static void insert(ProductEntity entity) throws SQLException {
        try (Connection c = Main.getConnection()) {

            String sql = "Insert into product(Title, ProductType, ArticleNumber, Image, ProductionPersonCount," +
                    " ProductionWorkshopNumber, MinCostForAgent) values (?,?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getProductType());
            ps.setString(3, entity.getArticleNumber());
            ps.setString(4, entity.getImage());
            ps.setInt(5, entity.getProductPersonCount());
            ps.setInt(6, entity.getProductionWorkshopNumber());
            ps.setInt(7, entity.getMinCostForAgent());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()) {
                entity.setId(keys.getInt(1));
                return;
            }

            throw new SQLException("Entity not added");
        }
    }

    public static ProductEntity selectById(int id) throws SQLException {
        try (Connection c = Main.getConnection()) {

            String sql = "Select * from product where id = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return new ProductEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("ArticleNumber"),
                        resultSet.getString("Image"),
                        resultSet.getInt("ProductionPersonCount"),
                        resultSet.getInt("ProductionWorkshopNumber"),
                        resultSet.getInt("MinCostForAgent")
                );
            }
            return null;
        }
    }

    public static List<ProductEntity> selectAll() throws SQLException {
        try (Connection c = Main.getConnection()) {

            String sql = "Select * from product";

            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<ProductEntity> list= new ArrayList<>();

            while (resultSet.next()){
                list.add(new ProductEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("ArticleNumber"),
                        resultSet.getString("Image"),
                        resultSet.getInt("ProductionPersonCount"),
                        resultSet.getInt("ProductionWorkshopNumber"),
                        resultSet.getInt("MinCostForAgent")
                ));
            }
            return list;
        }
    }

    public static void update(ProductEntity entity) throws SQLException {
        try (Connection c = Main.getConnection()) {

            String sql = "Update product SET Title = ?, ProductType = ?, ArticleNumber = ?, Image = ?," +
                    " ProductionPersonCount = ?, ProductionWorkshopNumber = ?, MinCostForAgent = ? where id =?";

            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getProductType());
            ps.setString(3, entity.getArticleNumber());
            ps.setString(4, entity.getImage());
            ps.setInt(5, entity.getProductPersonCount());
            ps.setInt(6, entity.getProductionWorkshopNumber());
            ps.setInt(7, entity.getMinCostForAgent());
            ps.setInt(8,entity.getId());

            ps.executeUpdate();

        }
    }

    public static void delete(int id) throws SQLException {
        try (Connection c = Main.getConnection()) {

            String sql = "Delete from product where id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static void deleteEntity(ProductEntity entity) throws SQLException{
        delete(entity.getId());
    }
}
