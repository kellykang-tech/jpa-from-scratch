package jpa.jpashop.web;

import jpa.jpashop.domain.item.Book;
import jpa.jpashop.domain.item.Item;
import jpa.jpashop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    // 상품 등록 폼 요청
    @RequestMapping(value = "/items/new", method = RequestMethod.GET)
    public String createForm() {
        return "items/createItemForm";
    }

    // 상품 등록 요청
    @RequestMapping(value = "/items/new", method = RequestMethod.POST)
    public String create(Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    // 상품 목록 요청
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    // 상품 수정 폼 요청
    @RequestMapping(value = "/items/{itemId}/edit", method = RequestMethod.GET)
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }

    // 상품 수정
    @RequestMapping(value = "/items/{itemId}/edit", method = RequestMethod.POST)
    public String updateItem(@ModelAttribute("item") Book item) {

        itemService.saveItem(item);
        return "redirect:/items";
    }
}
