package com.example.demo3.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity // このクラスをEntityとして使うためのアノテーション
@Data // Getter、Setterを省略するためのLombokのアノテーション
public class Posts {
     @Id // postsテーブルのプライマリーキーidに付けるアノテーション
     @GeneratedValue(strategy = GenerationType.IDENTITY) //idがMySQLのauto_incrementの場合、自動生成させるためにアノテーションを付ける
     // postsテーブルのid
     private Long id;

     private String title;

     private String text;

}
