package com.place.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class PlaceDAO implements PlaceDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM place where plc_no = ?";
	private static final String GET_BY_CAMP = "SELECT * FROM place where camp_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM place order by plc_no";
	private static final String INSERT_STMT = "INSERT INTO place (camp_no,plc_name,ppl,max_ppl,pc_wkdy,pc_wknd) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE place set plc_name=?,ppl=?,max_ppl=?,pc_wkdy=?,pc_wknd=? where plc_no = ?";
	private static final String UPDATESTAT = "UPDATE place set open_stat=? where plc_no = ?";
	private static final String DELETE = "DELETE FROM place where camp_no = ?";

	public PlaceVO findByPrimaryKey(Integer plc_no) {

		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, plc_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setPlc_no(rs.getInt("plc_no"));
				placeVO.setCamp_no(rs.getInt("camp_no"));
				placeVO.setPlc_name(rs.getString("plc_name"));
				placeVO.setPpl(rs.getInt("ppl"));
				placeVO.setMax_ppl(rs.getInt("max_ppl"));
				placeVO.setPc_wkdy(rs.getInt("pc_wkdy"));
				placeVO.setPc_wknd(rs.getInt("pc_wknd"));
				placeVO.setOpen_stat(rs.getInt("open_stat"));
				placeVO.setPlc_stat(rs.getInt("plc_stat"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return placeVO;
	}

	public List<PlaceVO> findByCampno(Integer camp_no) {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_CAMP);
			pstmt.setInt(1, camp_no);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setPlc_no(rs.getInt("plc_no"));
				placeVO.setCamp_no(rs.getInt("camp_no"));
				placeVO.setPlc_name(rs.getString("plc_name"));
				placeVO.setPpl(rs.getInt("ppl"));
				placeVO.setMax_ppl(rs.getInt("max_ppl"));
				placeVO.setPc_wkdy(rs.getInt("pc_wkdy"));
				placeVO.setPc_wknd(rs.getInt("pc_wknd"));
				placeVO.setOpen_stat(rs.getInt("open_stat"));
				placeVO.setPlc_stat(rs.getInt("plc_stat"));
				list.add(placeVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public List<PlaceVO> getAll() {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setPlc_no(rs.getInt("plc_no"));
				placeVO.setCamp_no(rs.getInt("camp_no"));
				placeVO.setPlc_name(rs.getString("plc_name"));
				placeVO.setPpl(rs.getInt("ppl"));
				placeVO.setMax_ppl(rs.getInt("max_ppl"));
				placeVO.setPc_wkdy(rs.getInt("pc_wkdy"));
				placeVO.setPc_wknd(rs.getInt("pc_wknd"));
				placeVO.setOpen_stat(rs.getInt("open_stat"));
				placeVO.setPlc_stat(rs.getInt("plc_stat"));
				list.add(placeVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void insert(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, placeVO.getCamp_no());
			pstmt.setString(2, placeVO.getPlc_name());
			pstmt.setInt(3, placeVO.getPpl());
			pstmt.setInt(4, placeVO.getMax_ppl());
			pstmt.setInt(5, placeVO.getPc_wkdy());
			pstmt.setInt(6, placeVO.getPc_wknd());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	
	@Override
	public void insert2(PlaceVO placeVO, Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, placeVO.getCamp_no());
			pstmt.setString(2, placeVO.getPlc_name());
			pstmt.setInt(3, placeVO.getPpl());
			pstmt.setInt(4, placeVO.getMax_ppl());
			pstmt.setInt(5, placeVO.getPc_wkdy());
			pstmt.setInt(6, placeVO.getPc_wknd());
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1;"); //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateStat(Integer plc_no, Integer open_stat) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTAT);
			pstmt.setInt(1, open_stat);
			pstmt.setInt(2, plc_no);
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
}
