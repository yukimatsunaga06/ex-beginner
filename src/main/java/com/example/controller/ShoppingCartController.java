package com.example.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;


@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ServletContext application;

	@Autowired
	private HttpSession session;


	@RequestMapping("")
	public String index(Model model) {
		List<Item> itemList = new LinkedList<>();

		Item item1 = new Item();
		item1.setName("手帳ノート");
		item1.setPrice(1000);
		itemList.add(item1);

		Item item2 = new Item();
		item2.setName("文房具セット");
		item2.setPrice(1500);
		itemList.add(item2);

		Item item3 = new Item();
		item3.setName("ファイル");
		item3.setPrice(2000);
		itemList.add(item3);

		application.setAttribute("itemList", itemList);

		@SuppressWarnings("unchecked")
		List<Item> cartItemList = (List<Item>) session.getAttribute("cartItemList");

		int totalPrice = 0;
		if (cartItemList == null) {
			// カートが空なら0件のリストを入れる
			session.setAttribute("cartItemList", new LinkedList<>());
		} else {
			// カートが空でなければ商品合計金額を計算
			totalPrice = calcTotalPrice(cartItemList);
		}
		model.addAttribute("totalPrice", totalPrice);

		return "item-and-cart";
	}


	@RequestMapping("incart")
	public String inCart(String index, Model model) {

		@SuppressWarnings("unchecked")
		List<Item> itemList = (List<Item>) application.getAttribute("itemList");
		Item item = itemList.get(Integer.parseInt(index));

		@SuppressWarnings("unchecked")
		List<Item> cartItemList = (List<Item>) session.getAttribute("cartItemList");
		cartItemList.add(item);

		return index(model);
	}


	@RequestMapping("delete")
	public String delete(String index, Model model) {
		@SuppressWarnings("unchecked")
		List<Item> cartItemList = (List<Item>) session.getAttribute("cartItemList");
		cartItemList.remove(Integer.parseInt(index));

		return index(model);
	}



	private Integer calcTotalPrice(List<Item> itemList) {
		Integer totalPrice = 0;

		for (Item item : itemList) {
			totalPrice += item.getPrice();
		}

		return totalPrice;
	}

}
