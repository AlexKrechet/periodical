package ua.andrii.project_19.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexJspCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/index.jsp";
    }
}
