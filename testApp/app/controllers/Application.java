package controllers;

import java.util.List;

import models.Message;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;
import views.html.index;

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

}
