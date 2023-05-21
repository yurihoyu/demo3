package com.example.demo3.controller;

//import追加
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo3.data.entity.Posts;
import com.example.demo3.data.repository.PostsRepository;
import com.example.demo3.form.PostsForm;
//アノテーションを追加
@Controller
public class PostsController {
  // @Autowiredアノテーションを付けると、SpringBootが自動でインスタンスをInjectします。
  @Autowired
  private PostsRepository postsRepository;


  // @RequestMapping(path = "/posts", method =RequestMethod.GET)の省略版。
  // HTTPのメソッドGETのみ受け付けます。
  @GetMapping("/posts")
  public String getPosts(Model model) {

      // ユーザーリスト取得処理を追加
      List<Posts> posts = postsRepository.findAll();
  // 取得したリストをテンプレートに渡す
      model.addAttribute("posts", posts);

      // テンプレートは src/main/resources/templates/posts.htmlとします。
      return "posts";
  }

  // getNewPostsメソッドを追加
  @GetMapping("/newposts")
  public String getNewPosts(Model model) {
      // Modelに空のUserFormを追加
      PostsForm postsForm = new PostsForm();
      model.addAttribute("postsForm", postsForm);
      // テンプレートは src/main/resources/templates/newposts.htmlとします。
      return "newposts";
  }

  // マッピング設定
  @PostMapping("/newposts")
  public String registerPosts(@Validated PostsForm postsForm,
BindingResult bindingResult) {
          // バリデーションの結果、エラーがあるかどうかチェック
      if (bindingResult.hasErrors()) {
          // エラーがある場合はユーザー登録画面を返す
          return "newposts";
      }

      Posts posts = new Posts();
      posts.setTitle(postsForm.getTitle());
      posts.setText(postsForm.getText());

      // データベースに保存
      postsRepository.save(posts);
      // ユーザ一覧画面へリダイレクト
      // ユーザ一覧画面へリダイレクト
      return "redirect:/posts";
  }

      // deletePostメソッドを追加
      // リクエストマッピング設定を追加
      @PostMapping("/posts/delete/{id}")
      // 処理の中でidを使えるように、引数にidを追加
      public String deletePost(@PathVariable Long id) {
      // 指定したIDのユーザーを削除
      postsRepository.deleteById(id);
      return "redirect:/posts";
  }
}
