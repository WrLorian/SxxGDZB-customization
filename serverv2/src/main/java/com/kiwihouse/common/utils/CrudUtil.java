package com.kiwihouse.common.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.dto.AlarmMsg;

/**
 * @author yjzn
 * @date 2020-1-16 17:50:08
 */
public class CrudUtil {
 
	public void insert(String alarm_id,String kwh,String eqpt_sn,String site_id){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "insert into electric_energy(alarm_id,kwh,eqpt_sn,site_id) values(" +
					alarm_id +
					","+kwh +
					","+eqpt_sn +
					","+site_id +
					")";
			int num = st.executeUpdate(sql);  //update
			if(num>0){
				System.out.println("插入成功！！");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}

	public static void BatchInsert(ArrayList<LinkedHashMap<Object, Object>> list){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "insert into electric_energy(alarm_id,kwh,eqpt_sn,group_id) values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			for (LinkedHashMap<Object, Object> map : list) {
				try {
					String alarmMsg = (String) map.get("alarm_msg");
					AlarmMsg kwhDto = JSONObject.parseObject(alarmMsg, AlarmMsg.class);
					pstmt.setInt(1, (Integer) map.get("alarm_id"));
					pstmt.setString(2, kwhDto.getKwh());
					pstmt.setString(3, (String) map.get("eqpt_sn"));
					pstmt.setInt(4, (Integer) map.get("group_id"));
					pstmt.addBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				pstmt.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}

	/**
	 * 查询该groupId所有设备的最近一条测量数据-用于记录设备的电能
	 * @param groupId
	 * @return
	 */
	public static ArrayList<LinkedHashMap<Object, Object>> queryKwh(String groupId){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<LinkedHashMap<Object, Object>> list = new ArrayList<>();

		try{
			conn = JdbcUtils.getConnection();
			String sql = "select alarm_id,alarm_msg,ai.eqpt_sn,e.group_id " +
					" from alarm_info as ai" +
					" left join equipment as e on ai.eqpt_sn=e.eqpt_sn" +
					" WHERE ai.alarm_id in (" +
					" select max(alarm_id)" +
					" from alarm_info as ai" +
					" left join equipment as e on ai.eqpt_sn=e.eqpt_sn" +
					" where ai.alarm_type=1 and e.group_id=" + groupId +
					" group by ai.eqpt_sn" +
					")";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			list = populate(rs);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, st, rs);
		}

		return list;
	}

	/**
	 * 无需对象类型，将一个结果集转换成一个List，
	 * 每一条记录都用LinkedHashMap保存，保证表中列的顺序，然后添加到List中保证记录的顺序，
	 * 取值时遍历即可，在一定程度上简化了取值操作
	 * @param rs 需要转换的结果集
	 * @return 转换后的List
	 */
	private static ArrayList<LinkedHashMap<Object, Object>> populate(ResultSet rs ){
		ResultSetMetaData rsmd = null;
		ArrayList list = null;
		try {
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			list = new ArrayList();
			while(rs.next()) {
				LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
				for(int i=1; i<=columnCount; i++) {
					lhm.put(rsmd.getColumnName(i),rs.getObject(i));
				}
				list.add(lhm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}