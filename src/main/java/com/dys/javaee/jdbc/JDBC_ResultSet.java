package com.dys.javaee.jdbc;

import java.sql.*;

public class JDBC_ResultSet {

    public void t01() throws Exception {

        Connection connection = null;

        PreparedStatement ps = connection.prepareStatement("select * from new_table");
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (rs.next()) {
            StringBuilder columnName = new StringBuilder();
            StringBuilder columnValue = new StringBuilder();

            for (int i = 1; i <= columnCount; i++) {
                String name = rsmd.getColumnName(i);
                String value = rs.getString(i);
                if (value == null) {
                    continue;
                }

                Integer columnType = rsmd.getColumnType(i);
                switch (columnType) {
                    case Types.CHAR:
                    case Types.VARCHAR:
                    case Types.LONGVARCHAR:
                        columnName.append("`").append(name).append("`,");
                        columnValue.append("'").append(value).append("'").append(",");
                        break;
                    case Types.SMALLINT:
                    case Types.INTEGER:
                    case Types.BIGINT:
                    case Types.FLOAT:
                    case Types.DOUBLE:
                    case Types.NUMERIC:
                    case Types.BIT:
                    case Types.DECIMAL:
                    case Types.TINYINT:
                    case Types.REAL:
                        columnName.append("`").append(name).append("`,");
                        columnValue.append(value).append(",");
                        break;
                    case Types.DATE:
                    case Types.TIME:
                    case Types.TIMESTAMP:
                        columnName.append("`").append(name).append("`,");
                        columnValue.append(value).append(",");
                        break;
                    default:
                        System.out.println("name" + name + ", type = " + columnType + ", value = " + value);
                        columnName.append("`").append(name).append("`,");
                        columnValue.append("'").append(value).append("',");
                }
            }
            StringBuilder insertSQL = new StringBuilder();
            insertSQL.append("insert into ").append("new _table").append("(");
            insertSQL.append(columnName.toString()).append(")");
            insertSQL.append("values").append("(");
            insertSQL.append(columnValue.toString());
            insertSQL.append(");");
            System.out.println(insertSQL.toString().replaceAll(",\\)values", "\\)values").replaceAll(",\\);", "\\);"));
        }

    }

}
