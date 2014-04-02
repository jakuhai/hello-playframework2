package controllers;

import java.util.List;

import models.Message;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;
import views.html.delete;
import views.html.edit;
import views.html.index;
import views.html.item;

public class Application extends Controller {

	public static class FindForm {
		@Required
		public String input;
	}

	// Form用の内部クラス
	public static class SampleForm{
		private String message;
		public String getMessage(){ return message; }
		public void setMessage(String message){ this.message = message; }
	}

	// ルートにアクセスした際のAction
    public static Result index() {

    	List<Message> datas = Message.find.all();

        return ok(index.render("データベースサンプル。",datas));
    }

    public static Result add(){
    	Form<Message> f = new Form<Message>(Message.class);
    	return ok(add.render("投稿フォーム",f));
    }

    // /createにアクセスした際のAction
 	public static Result create(){
 		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
 		if (!f.hasErrors()){
 			Message data = f.get();
 			data.save();
 			return redirect("/");
 		} else {
 			return badRequest(add.render("ERROR", f));
 		}
 	}

 	public static Result setitem(){
 		Form<Message> f = new Form<Message>(Message.class);
 		return ok(item.render("ID番号を入力。",f));
 	}

 	public static Result edit(){
 		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
 		if (!f.hasErrors()){
 			Message obj = f.get();

 			Long id = obj.id;
 			obj = Message.find.byId(id);
 			if(obj != null){
 				f = new Form<Message>(Message.class).fill(obj);
 				return ok(edit.render("ID="+ id + "の投稿を編集。", f));
 			}else{
 				return ok(item.render("ERROR：IDの投稿が見つかりません", f));
 			}

 		} else {
 			return badRequest(edit.render("ERROR：入力に問題があります。", f));
 		}
 	}

 	public static Result update(){
 		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
 		if (!f.hasErrors()){
 			Message obj = f.get();
 			obj.update();
 			return redirect("/");
 		} else {
 			return badRequest(edit.render("ERROR：入力に問題があります。", f));
 		}
 	}

 	public static Result delete(){
 		Form<Message> f = new Form<Message>(Message.class);
 		return ok(delete.render("削除するID番号。",f));
 	}

 	public static Result remove(){

 		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
 		if (!f.hasErrors()){
 			Message obj = f.get();
 			obj = Message.find.byId(obj.id);
 			if(obj != null){
 				obj.delete();
 				return redirect("/item");
 			}else{
 				return ok(delete.render("ERROR：IDの投稿が見つかりません", f));
 			}


 		} else {
 			return badRequest(delete.render("ERROR：入力に問題があります。", f));
 		}

 	}

}
