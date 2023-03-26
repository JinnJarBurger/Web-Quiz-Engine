package net.therap.webQuizEngine.utils;

import net.therap.webQuizEngine.model.Action;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Component
public class CommonUtils implements ApplicationContextAware {

    private static MessageSourceAccessor msa;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        msa = (MessageSourceAccessor) applicationContext.getBean("messageSourceAccessor");
    }

    public static void setupReferenceData(Action action, RedirectAttributes ra) {
        switch (action) {
            case SAVE:
                ra.addFlashAttribute("addMessage", msa.getMessage("success.add"));
                break;
            case UPDATE:
                ra.addFlashAttribute("updateMessage", msa.getMessage("success.update"));
                break;
            case DELETE:
                ra.addFlashAttribute("deleteMessage", msa.getMessage("success.delete"));
                break;
        }
    }
}
