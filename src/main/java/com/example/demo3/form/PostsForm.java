package com.example.demo3.form;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostsForm {

	// titleへのバリデーション設定を追加
	@Size(min = 1, max = 200)
	private String title;
	// textへのバリデーション設定を追加
	@Size(min = 1, max = 200)
	private String text;
}