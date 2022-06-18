package designpatterns;

public class Database {

      private static Database instance=null;
      public static final Object obj = new Object();

      private Database(){

      }

      public static Database getInstance(){

            if(null==instance){

                  synchronized (obj){

                        if(null==instance){
                             return new Database();
                        }
                  }
                  return new Database();
            }
            return instance;
      }

      // TODO -> to solve concurrency issue make Db object at startup but it won't work with Parameterized constructor
      //  like username and password

}
