package zhiwenyan.cmccaifu.com.android2017.Gson;

/**
 * Description:
 * Data：5/14/2018-5:13 PM
 *
 * @author yanzhiwen
 */
public class GsonUtil {
//    //从流中读取List<User>
//    public List<User> readJsonStream(InputStream in) throws IOException {
//        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
//        try {
//            return readUserArray(reader);//读取用户列表
//        } finally {
//            reader.close();//关闭流
//        }
//    }
//
//    //读取List<User>
//    public List<User> readUserArray(JsonReader reader) throws IOException {
//        List<User> users = new ArrayList<User>();
//        reader.beginArray();//开始读取数组
//        while (reader.hasNext()) {//是否还有下个元素
//            users.add(readUser(reader));//读取下个元素
//        }
//        reader.endArray();//结束读取数组
//        return users;
//    }
//
//    //读取User对象
//    public User readUser(JsonReader reader) throws IOException {
//        String name = null;
//        int age = -1;
//        List<Double> geo = null;
//        reader.beginObject();//开始读取对象
//        while (reader.hasNext()) {//是否还有下个元素
//            String name = reader.nextName();//读取下一个json属性名
//            //判断属性名是哪一个
//            if (name.equals("name")) {
//                name = reader.nextString();
//            } else if (name.equals("age")) {
//                age = reader.nextInt();
//            } else if (name.equals("geo") && reader.peek() != JsonToken.NULL) {
//                geo = readDoublesArray(reader);
//            } else {
//                reader.skipValue();//忽略没有匹配到内容的值
//            }
//        }
//        reader.endObject();//结束读取对象
//        return new User(name, age, geo);
//    }
//
//    //读取经纬度
//    public List<Double> readDoublesArray(JsonReader reader) throws IOException {
//        List<Double> doubles = new ArrayList<>();
//
//        reader.beginArray();
//        while (reader.hasNext()) {
//            doubles.add(reader.nextDouble());
//        }
//        reader.endArray();
//        return doubles;
//    }
}
