package jzt.wcs.wserver.db;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class cls_db_oracle {
	public static String g_Oracle_Char = "1";
	public static String g_Oracle_Int = "2";
	public static String g_Oracle_Cursor = "3";
	public static String g_Oracle_InPut = "1";
	public static String g_Oracle_OutPut = "2";
	private String driverName = "oracle.jdbc.driver.OracleDriver";
	public static String db_url = "jdbc:oracle:thin:@10.3.4.91:1521:cts";
	public static String db_user = "cts";
	public static String db_password = "jointown!2013!nykj";
	public Connection m_conn = null;


	public cls_db_oracle() throws ClassNotFoundException, SQLException {
		Class.forName(this.driverName);

		this.m_conn = DriverManager.getConnection(db_url, db_user, db_password);
//		this.m_conn = jdbcTemplate.getDataSource().getConnection();
	}

	public void mCloseConn() throws SQLException {
		this.m_conn.close();
		this.m_conn = null;
	}

	public JSONArray mOpenSql(String _Sql) throws SQLException, JSONException,
			ClassNotFoundException {
		JSONArray rlt_json_array = new JSONArray();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pstmt = this.m_conn.prepareStatement(_Sql);
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			JSONObject rlt_json_data = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				if (value == null) {
					value = "";
				}
				rlt_json_data.put(columnName, value);
			}
			rlt_json_array.add(rlt_json_data);
		}
		this.m_conn.close();
		return rlt_json_array;
	}

	public JSONArray mExceProcdure(String _ProcName, String[][] _Param)
			throws ClassNotFoundException, SQLException {
		JSONArray rlt_json_array = new JSONArray();
		JSONObject rlt_json_data = new JSONObject();
		CallableStatement stmt = null;
		stmt = this.m_conn.prepareCall("{call " + _ProcName + " }");
		for (int i = 0; i < _Param.length; i++) {
			if (_Param[i][2].equals(g_Oracle_InPut)) {
				stmt.setString(i + 1, _Param[i][3]);
			} else if (_Param[i][2].equals(g_Oracle_OutPut)) {
				if (_Param[i][1].equals(g_Oracle_Char)) {
					stmt.registerOutParameter(i + 1, 1);
				} else if (_Param[i][1].equals(g_Oracle_Int)) {
					stmt.registerOutParameter(i + 1, 6);
				} else if (_Param[i][1].equals(g_Oracle_Cursor)) {
					stmt.registerOutParameter(i + 1, -10);
				}
			}
		}
		int _iRlt = stmt.executeUpdate();

		int _iFind = -1;
		for (int i = 0; i < _Param.length; i++) {
			if (_Param[i][2].equals(g_Oracle_OutPut)) {
				if (_Param[i][1].equals(g_Oracle_Char)) {
					_Param[i][3] = stmt.getString(i + 1);
					rlt_json_data.put(_Param[i][0], _Param[i][3]);
				}
			}
		}
		if (_iFind == -1)
			rlt_json_data.put("result", Integer.valueOf(_iRlt));
		rlt_json_array.add(rlt_json_data);
		return rlt_json_array;
	}

	public JSONArray mGetRsOfExceProcdure(String _ProcName, String[][] _Param)
			throws ClassNotFoundException, SQLException {
		JSONArray rlt_json_array = new JSONArray();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		Class.forName(this.driverName);
		conn = DriverManager.getConnection(db_url, db_user, db_password);
		stmt = conn.prepareCall("{call " + _ProcName + " }");
		for (int i = 0; i < _Param.length; i++) {
			if (_Param[i][2].equals(g_Oracle_InPut)) {
				stmt.setString(i + 1, _Param[i][3]);
			} else if (_Param[i][2].equals(g_Oracle_OutPut)) {
				if (_Param[i][1].equals(g_Oracle_Char)) {
					stmt.registerOutParameter(i + 1, 1);
				} else if (_Param[i][1].equals(g_Oracle_Int)) {
					stmt.registerOutParameter(i + 1, 6);
				} else if (_Param[i][1].equals(g_Oracle_Cursor)) {
					stmt.registerOutParameter(i + 1, -10);
				}
			}
		}
		stmt.setQueryTimeout(15);
		stmt.executeUpdate();
		for (int i = 0; i < _Param.length; i++) {
			if (_Param[i][2].equals(g_Oracle_OutPut)) {
				if (_Param[i][1].equals(g_Oracle_Char)) {
					_Param[i][3] = stmt.getString(i + 1);
				} else if (_Param[i][1].equals(g_Oracle_Cursor)) {
					rs = (ResultSet) stmt.getObject(i + 1);
				}
			}
		}
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			JSONObject rlt_json_data = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				if (value == null) {
					value = "";
				}
				rlt_json_data.put(columnName, value);
			}
			rlt_json_array.add(rlt_json_data);
		}
		this.m_conn.close();
		return rlt_json_array;
	}

	public JSONArray mGetStrOfExceFunction(String _ProcName, String[][] _Param)
			throws ClassNotFoundException, SQLException {
		JSONArray rlt_json_array = new JSONArray();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		Class.forName(this.driverName);
		conn = DriverManager.getConnection(db_url, db_user, db_password);
		stmt = conn.prepareCall("{?=call " + _ProcName + " }");
		stmt.registerOutParameter(1, 1);
		for (int i = 0; i < _Param.length; i++) {
			if (_Param[i][2].equals(g_Oracle_InPut)) {
				stmt.setString(i + 2, _Param[i][3]);
			} else if (_Param[i][2].equals(g_Oracle_OutPut)) {
				if (_Param[i][1].equals(g_Oracle_Char)) {
					stmt.registerOutParameter(i + 2, 1);
				} else if (_Param[i][1].equals(g_Oracle_Int)) {
					stmt.registerOutParameter(i + 2, 6);
				} else if (_Param[i][1].equals(g_Oracle_Cursor)) {
					stmt.registerOutParameter(i + 2, -10);
				}
			}
		}
		stmt.executeUpdate();
		JSONObject rlt_json_data = new JSONObject();
		String _sRlt = stmt.getString(1);

		rlt_json_data.put("result", _sRlt);

		return rlt_json_array;
	}
}
