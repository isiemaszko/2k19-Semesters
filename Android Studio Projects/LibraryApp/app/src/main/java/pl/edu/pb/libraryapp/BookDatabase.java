package pl.edu.pb.libraryapp;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1,exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static volatile BookDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BookDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (BookDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            BookDatabase.class, "book_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback=new RoomDatabase.Callback(){
        @Override
        public void onOpen(@Nullable SupportSQLiteDatabase db){
            super.onOpen(db);
            databaseWriteExecutor.execute(()->{
                BookDao dao=INSTANCE.bookDao();

                Book book=new Book("Ostatnia piosenka","Nicholas Sparks");
                dao.insert(book);
                Book book1=new Book("Naznaczona","Kristin Cast");
                dao.insert(book1);
                Book book2=new Book("Igrzyska Smierci TOM 1","Suzanne Collins");
                dao.insert(book2);
                Book book3=new Book("W pierścieniu ognia TOM 2","Suzanne Collins");
                dao.insert(book3);
                Book book4=new Book("Kosogłos TOM 3","Suzanne Collins");
                dao.insert(book4);
                Book book5=new Book("Papierowe Miasta","John Green");
                dao.insert(book5);
            });
        }
    };

}
