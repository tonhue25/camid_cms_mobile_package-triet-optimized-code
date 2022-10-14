package co.siten.myvtg.configuration;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
// prefix app, find app.* values
public class MenuConfig {
    private List<Menu> menus = new ArrayList<>();

    public MenuConfig(){}

    public MenuConfig(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public static class Menu {
        private String code;
        private String name;
        private String icon;

        public Menu(){}
        public Menu(String code, String name, String icon) {
            this.code = code;
            this.name = name;
            this.icon = icon;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
