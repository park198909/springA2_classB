package org.koreait.restcontrollers.files;

import lombok.RequiredArgsConstructor;
import org.koreait.models.file.FileDeleteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class FileDeleteController {
    private final FileDeleteService deleteService;

    @RequestMapping("/file/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {

        deleteService.delete(id);

        String script = String.format("if (typeof parent.fileDeleteCallback == 'function') parent.fileDeleteCallback(%d);", id);
        model.addAttribute("script", script);
        return "commons/execute_script";
    }
}
