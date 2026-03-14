package dao;

import java.sql.*;
import java.util.*;
import model.Employe;

public class EmployeDAO {

    private String jdbcURL="jdbc:mysql://localhost:3306/entreprise";
    private String jdbcUsername="root";
    private String jdbcPassword="";

    private static final String INSERT_EMP =
    "INSERT INTO employe (nom,prenom,poste,salaire) VALUES (?,?,?,?)";

    private static final String SELECT_ALL =
    "SELECT * FROM employe";

    private static final String DELETE_EMP =
    "DELETE FROM employe WHERE id=?";

    private static final String UPDATE_EMP =
    "UPDATE employe SET nom=?,prenom=?,poste=?,salaire=? WHERE id=?";

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
    }

    public void insertEmploye(Employe emp) throws SQLException{

        Connection con=getConnection();
        PreparedStatement ps=con.prepareStatement(INSERT_EMP);

        ps.setString(1,emp.getNom());
        ps.setString(2,emp.getPrenom());
        ps.setString(3,emp.getPoste());
        ps.setDouble(4,emp.getSalaire());

        ps.executeUpdate();
    }

    public List<Employe> selectAllEmployes(){

        List<Employe> list=new ArrayList<>();

        try{

            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement(SELECT_ALL);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                int id=rs.getInt("id");
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                String poste=rs.getString("poste");
                double salaire=rs.getDouble("salaire");

                list.add(new Employe(id,nom,prenom,poste,salaire));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public void deleteEmploye(int id) throws SQLException{

        Connection con=getConnection();
        PreparedStatement ps=con.prepareStatement(DELETE_EMP);

        ps.setInt(1,id);
        ps.executeUpdate();
    }

    public void updateEmploye(Employe emp) throws SQLException{

        Connection con=getConnection();
        PreparedStatement ps=con.prepareStatement(UPDATE_EMP);

        ps.setString(1,emp.getNom());
        ps.setString(2,emp.getPrenom());
        ps.setString(3,emp.getPoste());
        ps.setDouble(4,emp.getSalaire());
        ps.setInt(5,emp.getId());

        ps.executeUpdate();
    }
}