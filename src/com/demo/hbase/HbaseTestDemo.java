//package com.demo.hbase;
//
//import javafx.scene.control.Cell;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.client.*;
//
//import javax.security.auth.login.Configuration;
//import java.io.IOException;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description hbase-java 测试（hbase 单机版测试）
// * @date 2021/2/22 16:09
// * @see
// */
//public class HbaseTestDemo {
//
//    public static Configuration conf = null;
//    public static Connection conn = null;
//
//    /**
//     * 类级别的初始化，只是在类加载的时候做一次 配置zookeeper的端口2181
//     * 配置zookeeper的仲裁主机名centos，如果有多个机器，主机名间用冒号隔开 配置hbase master
//     * 还有一种方式是new一个configuration对象，然后用addresource方法去添加xml配置文件 但是像这样显式的配置是会覆盖xml里的配置的
//     */
//    static {
//        conf = HBaseConfiguration.create();
//        // hbase 主机
//        conf.set("hbase.zookeeper.quorum", "hbase01");
//        conf.set("hbase.zookeeper.property.clientPort", "2181");
//        conf.set("hbase.master", "hbase01:16000");
//        conf.setInt("hbase.regionserver.port", 16201);
//        conf.setInt("hbase.rpc.timeout", 200);
//        conf.setInt("hbase.client.operation.timeout", 300);
//        conf.setInt("hbase.client.scanner.timeout.period", 200);
//
//        try {
//
//            conn = ConnectionFactory.createConnection(conf);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 建表，建列族
//     *
//     * @param tablename,
//     * @param ColumnFamilys NamespaceDescriptor:维护命名空间的信息,但是namespace，一般用shell来建立
//     *                      Admin:提供了一个接口来管理 HBase 数据库的表信息
//     *                      HTableDescriptor:维护了表的名字及其对应表的列族,通过HTableDescriptor对象设置表的特性
//     *                      HColumnDescriptor:维护着关于列族的信息,可以通过HColumnDescriptor对象设置列族的特性
//     */
//
//    public void createtable() throws IOException {
//        String tablename = "ns2:student";
//        String[] ColumnFamilys = new String[]{"data"};
//
//
//        Admin admin = conn.getAdmin();
//        HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tablename));
//        for (String family : ColumnFamilys) {
//            HColumnDescriptor columnfamily = new HColumnDescriptor(family);
//            table.addFamily(columnfamily);
//        }
//
//        if (admin.tableExists(TableName.valueOf(tablename))) {
//            System.out.println("Table Exists");
//        } else {
//            admin.createTable(table);
//            System.out.println("Table Created");
//            admin.close();
//        }
//    }
//
//    /**
//     * 插入数据,当指定rowkey已经存在，则会覆盖掉之前的旧数据
//     *
//     * @param tablename,
//     * @param rowkey,
//     * @param ColumnFamilys,
//     * @param columns,@values Table:用于与单个HBase表通信 Put:用来对单个行执行添加操作
//     */
//    public void insertdata() throws Exception {
//
//        String tablename = "ns2:student";
//        String rowkey = "1004";
//        String ColumnFamilys = "data";
//        String[] columns = new String[]{"name", "age", "adress"};
//        String[] values = new String[]{"zhangsan_44", "24", "guangzhou"};
//
//        Table table = conn.getTable(TableName.valueOf(tablename));
//        Put put = new Put(Bytes.toBytes(rowkey));
//        for (int i = 0; i < columns.length; i++) {
//            put.addColumn(Bytes.toBytes(ColumnFamilys), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
//        }
//        table.put(put);
//        System.out.println("data inserted");
//        table.close();
//    }
//
//    /**
//     * 扫描全表
//     *
//     * @param tablename
//     */
//    public void scantable() throws IOException {
//        String tablename = "ns2:student";
//        Scan scan = new Scan();
//        Table table = conn.getTable(TableName.valueOf(tablename));
//        ResultScanner rs = table.getScanner(scan);
//        for (Result result : rs) {
//            for (Cell cell : result.listCells()) {
//                System.out.println(Bytes.toString(cell.getRow()) + "    " + "column=" + Bytes.toString(cell.getFamily())
//                        + ":" + Bytes.toString(cell.getQualifier()) + " , value="
//                        + Bytes.toString(cell.getValue()));
//            }
//            System.out.println("");
//        }
//        rs.close();
//    }
//
//
//    /**
//     * 根据rowkey对表进行scan
//     *
//     * @param tablename
//     * @param rowkey    scan 'student',{ROWPREFIXFILTER => '1'}
//     */
//    public void scanrow() throws IOException {
//        String tablename = "ns2:student";
//        String rowkey = "1002";
//
//
//        Get get = new Get(Bytes.toBytes(rowkey));
//        Table table = conn.getTable(TableName.valueOf(tablename));
//        Result result = table.get(get);
//
//        for (Cell kv : result.listCells()) {
//            System.out.println(
//                    rowkey + "    column=" + Bytes.toString(kv.getFamily()) + ":" + Bytes.toString(kv.getQualifier())
//                            + "," + " , value=" + Bytes.toString(kv.getValue()));
//        }
//    }
//
//
//    /**
//     * 根据rowkey删除整行的所有列族、所有行、所有版本
//     *
//     * @param tablename
//     * @param rowkey
//     */
//    public void deleterow() throws IOException {
//        String tablename = "ns2:student";
//        String rowkey = "1001";
//
//        Table table = conn.getTable(TableName.valueOf(tablename));
//        Delete delete = new Delete(Bytes.toBytes(rowkey));
//
//        table.delete(delete);
//        table.close();
//
//        System.out.println("row : " + rowkey + " is deleted");
//    }
//
//
//    /**
//     * 删除某个row的指定列
//     *
//     * @param tablename
//     * @param rowkey
//     * @param columnfamily
//     * @param column
//     */
//    public void deletecol() throws IOException {
//
//        String tablename = "ns2:student";
//        String rowkey = "1002";
//        String columnfamily = "data";
//        String column = "age";
//
//
//        Table table = conn.getTable(TableName.valueOf(tablename));
//
//        Delete delete = new Delete(Bytes.toBytes(rowkey));
//        delete.deleteColumn(Bytes.toBytes(columnfamily), Bytes.toBytes(column));
//
//        table.delete(delete);
//        table.close();
//
//        System.out.println("row : " + rowkey + " is deleted");
//    }
//
//
//    /**
//     * 使用过滤器，获取18-20岁之间的学生信息
//     *
//     * @param tablename
//     * @param age
//     * @throws IOException
//     */
//    public void scanfilterage() throws IOException {
//
//        String tablename = "ns2:student";
//        String columnfamily = "data";
//        String startage = "23";
//        String endage = "23";
//
//        System.out.println("scanfilterage()");
//
//
//        SingleColumnValueFilter ft1 = new SingleColumnValueFilter(//
//                Bytes.toBytes(columnfamily),//
//                Bytes.toBytes("age"), //
//                CompareFilter.CompareOp.GREATER, //
//                Bytes.toBytes(startage)//
//        );
//
//        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
//        filterList.addFilter(ft1);
//
//        Scan scan = new Scan();
//        scan.setFilter(filterList);
//
//        Table table = conn.getTable(TableName.valueOf(tablename));
//        ResultScanner rs = table.getScanner(scan);
//        for (Result r : rs) {
//            for (Cell cell : r.listCells()) {
//                System.out.println(Bytes.toString(cell.getRow()) + "\t column = "
//                        + Bytes.toString(cell.getFamily()) + ":" + Bytes.toString(cell.getQualifier()) + "   value = "
//                        + Bytes.toString(cell.getValue()));
//            }
//            System.out.println("");
//        }
//        table.close();
//    }
//
//
//    @Test
//    public void testComboFilter() throws IOException {
//
//        Connection conn = ConnectionFactory.createConnection(conf);
//        TableName tname = TableName.valueOf("ns2:student");
//        Scan scan = new Scan();
//
//        //where ... f2:age <= 13
//        SingleColumnValueFilter ftl = new SingleColumnValueFilter(
//                Bytes.toBytes("data"),
//                Bytes.toBytes("age"),
//                CompareFilter.CompareOp.EQUAL,
//                // new BinaryComparator(Bytes.toBytes("21"))
//                Bytes.toBytes("22")
//        );
//
//        FilterList ft = new FilterList(FilterList.Operator.MUST_PASS_ALL);
//        ft.addFilter(ftl);
//        scan.setFilter(ft);
//
//
//        Table t = conn.getTable(tname);
//        ResultScanner rs = t.getScanner(scan);
//        for (Result result : rs) {
//            for (Cell cell : result.listCells()) {
//                System.out.println(Bytes.toString(cell.getRow()) + "    " + "column=" + Bytes.toString(cell.getFamily())
//                        + ":" + Bytes.toString(cell.getQualifier()) + " , value="
//                        + Bytes.toString(cell.getValue()));
//            }
//            System.out.println("");
//        }
//    }
//
//    /**
//     * 测试ValueFilter(值过滤器)
//     * 过滤value的值，含有指定的字符子串
//     */
//    @Test
//    public void testValueFilter() throws IOException {
//
//        Connection conn = ConnectionFactory.createConnection(conf);
//        TableName tname = TableName.valueOf("ns2:student");
//        Scan scan = new Scan();
//        ValueFilter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("22"));
//        scan.setFilter(filter);
//        Table t = conn.getTable(tname);
//        ResultScanner rs = t.getScanner(scan);
//        for (Result result : rs) {
//            for (Cell cell : result.listCells()) {
//                System.out.println(Bytes.toString(cell.getRow()) + "    " + "column=" + Bytes.toString(cell.getFamily())
//                        + ":" + Bytes.toString(cell.getQualifier()) + " , value="
//                        + Bytes.toString(cell.getValue()));
//            }
//            System.out.println("");
//        }
//    }
//
//
//    /**
//     * DependentColumnFilter 依赖列过滤器     自定义一种过滤基数
//     */
//    @Test
//    public void testDepFilter() throws IOException {
//        Connection conn = ConnectionFactory.createConnection(conf);
//
//        TableName tname = TableName.valueOf("ns2:student");
//        Scan scan = new Scan();
//        DependentColumnFilter filter = new DependentColumnFilter(//
//                Bytes.toBytes("data"),//
//                Bytes.toBytes("name"),//
//                true,//
//                CompareFilter.CompareOp.NOT_EQUAL,//
//                new BinaryComparator(Bytes.toBytes("22"))//
//        );
//        scan.setFilter(filter);
//        Table t = conn.getTable(tname);
//        ResultScanner rs = t.getScanner(scan);
//
//        for (Result result : rs) {
//            for (Cell cell : result.listCells()) {
//                System.out.println(Bytes.toString(cell.getRow()) + "    " + "column=" + Bytes.toString(cell.getFamily())
//                        + ":" + Bytes.toString(cell.getQualifier()) + " , value="
//                        + Bytes.toString(cell.getValue()));
//            }
//            System.out.println("");
//        }
//    }
//
//
//    @Test //扫描数据
//    public void getRow(String tableName, String rowKey) throws IOException {
//
//        TableName tname = TableName.valueOf("ns2:student");
//        Scan scan = new Scan();
//        Table t = conn.getTable(tname);
//        ResultScanner rs = t.getScanner(scan);
//        for (Result result : rs) {
//            for (Cell cell : result.rawCells()) {
//                System.out.println("行键:" + Bytes.toString(result.getRow()));
//                System.out.println("列族" + Bytes.toString(CellUtil.cloneFamily(cell)));
//                System.out.println("列:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
//                System.out.println("值:" + Bytes.toString(CellUtil.cloneValue(cell)));
//                System.out.println("时间戳:" + cell.getTimestamp());
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//
////		createtable("student", "imformation", "score");
////
////		insertdata("student", "1", "imformation", col1, val1);
////		insertdata("student", "1", "imformation", col2, val2);
////
////		deleterow("student", "1");
////		deletecol("student", "1", "imformation", "chinese");
////		deleteversion("student", "1", "imformation", 1533482642629L);
////		deletefamily("student", "imformation");
////		droptable("student");
////
////		scantable("student");
////		scanrow("student", "1");
////		scanspecifycolumn("student", "1", "imformation", "chinese");
////		scanspecifytimestamp("student", "imformation", 1533482642629L);
////		scanallversion("student", "1");
////		scanfilterage("student", 18, 20);
//
//    }
//}
