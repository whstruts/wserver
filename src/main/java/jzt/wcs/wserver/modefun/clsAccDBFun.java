package jzt.wcs.wserver.modefun;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jzt.wcs.wserver.db.cls_db_oracle;

import java.sql.SQLException;


public class clsAccDBFun {
	public JSONArray mAccDBFunction(String _modeName, String _InParadata)
			throws ClassNotFoundException, SQLException {
		JSONArray rlt_json_array = new JSONArray();
		String _sTable = "";
		String _sType = "";

		JSONObject json_main = JSONObject.parseObject(_InParadata);
		_sTable = json_main.getString("table").toLowerCase();

		_sType = json_main.getString("type").toLowerCase();

		JSONArray json_main_array = new JSONArray();
		json_main_array = JSONArray.parseArray(json_main.getString("data"));

		cls_db_oracle _db = new cls_db_oracle();
		if (_sType.equals("query")) {
			String sParam_data = json_main_array.getString(0);
			JSONObject json_sParam_data = JSONObject.parseObject(sParam_data);

			String sSQL = json_sParam_data.getString("sql");

			rlt_json_array = _db.mOpenSql(sSQL);
		} else if (_sType.equals("procedure")) {
			int _idpParamLen = json_main_array.size();
			String[][] _Param = new String[_idpParamLen][4];
			for (int i = 0; i < _idpParamLen; i++) {
				JSONObject _jsondata = JSONObject.parseObject(json_main_array
						.getString(i));
				_Param[i][0] = _jsondata.getString("_ParamName");
				_Param[i][1] = _jsondata.getString("_DataType");
				_Param[i][2] = _jsondata.getString("_InputType");
				_Param[i][3] = _jsondata.getString("_ParamText");
			}
			rlt_json_array = _db.mExceProcdure(_sTable, _Param);
		} else if (_sType.equals("getds")) {
			int _idpParamLen = json_main_array.size();
			String[][] _Param = new String[_idpParamLen][4];
			for (int i = 0; i < _idpParamLen; i++) {
				JSONObject _jsondata = JSONObject.parseObject(json_main_array
						.getString(i));
				_Param[i][0] = _jsondata.getString("_ParamName");
				_Param[i][1] = _jsondata.getString("_DataType");
				_Param[i][2] = _jsondata.getString("_InputType");
				_Param[i][3] = _jsondata.getString("_ParamText");
			}
			rlt_json_array = _db.mGetRsOfExceProcdure(_sTable, _Param);
		} else if (_sType.equals("function")) {
			int _idpParamLen = json_main_array.size();
			String[][] _Param = new String[_idpParamLen][4];
			for (int i = 0; i < _idpParamLen; i++) {
				JSONObject _jsondata = JSONObject.parseObject(json_main_array
						.getString(i));
				_Param[i][0] = _jsondata.getString("_ParamName");
				_Param[i][1] = _jsondata.getString("_DataType");
				_Param[i][2] = _jsondata.getString("_InputType");
				_Param[i][3] = _jsondata.getString("_ParamText");
			}
			rlt_json_array = _db.mGetStrOfExceFunction(_sTable, _Param);
		}
		return rlt_json_array;
	}
}
