package com.won983212.boardgame.global.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @ExceptionHandler(Throwable.class)
    @GetMapping("/error")
    public String error(Throwable throwable, HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String statusMsg = status.toString();
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(statusMsg));

        if (httpStatus.isSameCodeAs(HttpStatusCode.valueOf(403))) {
            return "redirect:/login";
        }

        model.addAttribute("title", httpStatus.value() + ": " + httpStatus.getReasonPhrase());
        model.addAttribute("message", throwable.getMessage());
        return "error";
    }
}
