
public class MainGoBang {
    public static void main(String[] args) {
        ViewGoBang viewgb = new ViewGoBang();
        ModelGoBang modelgb = new ModelGoBang(viewgb);
        ControllorGoBang controllergb = new ControllorGoBang(viewgb, modelgb);
    }
}
