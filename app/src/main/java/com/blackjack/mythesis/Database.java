package com.blackjack.mythesis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SILVA on 2019/05/19.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "jawa";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    public String search(String kata) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "latin", "jawa"};
        String sqlTable = "transliterasi";
        String args[] = {kata + ""};

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, "jawa=?", args, null, null, "latin ASC");

        String result = "";
        if (c.moveToFirst()) {
            do {
                result = c.getString(c.getColumnIndex(sqlSelect[1]));
            } while (c.moveToNext());
        }
        return result;
    }

    public ArrayList<String> searchLike(String kata) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "latin", "jawa"};
        String sqlTable = "transliterasi";
        String args[] = {""+kata + ""};
//        String args[] = {"%"+kata + "%"};

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, "jawa = ?", args, null, null, "id ASC");

        ArrayList<String> result = new ArrayList<>() ;
        if (c.moveToFirst()) {
            do {
                result.add(c.getString(c.getColumnIndex(sqlSelect[2])));
            } while (c.moveToNext());
        }
        return result;
    }

//    public void insert(Order order){
//        SQLiteDatabase db = getReadableDatabase();
//        String query = String.format("INSERT INTO OrderDetail(ProductId, ProductName, Quantity, Price, Discount) VALUES('%s','%s','%s','%s','%s');",
//                order.getProductId(),
//                order.getProductName(),
//                order.getQuanlity(),
//                order.getPrice(),
//                order.getDiscount());
//
//        db.execSQL(query);
//    }

    public void removeFromCart(String order) {

        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("DELETE FROM OrderDetail WHERE ProductId='" + order + "'");
        db.execSQL(query);
    }

    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }

}
