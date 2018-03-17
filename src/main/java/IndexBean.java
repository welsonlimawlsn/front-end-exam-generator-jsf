import br.com.welson.examgeneretor.persistence.dao.LoginDAO;
import br.com.welson.examgeneretor.persistence.model.support.Token;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexBean implements Serializable {
    private String messagem = "Woooorking";
    private final LoginDAO loginDAO;

    @Inject
    public IndexBean(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public void login() {
        Token token = loginDAO.loginReturningToken("welson", "welson");
        System.out.println(token);
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}
