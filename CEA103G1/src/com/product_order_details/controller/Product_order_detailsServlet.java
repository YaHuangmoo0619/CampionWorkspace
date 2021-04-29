package com.product_order_details.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product_order_details.model.*;

@WebServlet("/product_order_details/product_order_details.do")
public class Product_order_detailsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str1 = req.getParameter("prod_ord_no");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�q��s��");
				}
				String str2 = req.getParameter("prod_no");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer prod_ord_no = null;
				try {
					prod_ord_no = new Integer(str1);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�q��s���榡�����T");
				}
				Integer prod_no = null;
				try {
					prod_ord_no = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Product_order_detailsService product_order_detailsSvc = new Product_order_detailsService();
				Product_order_detailsVO product_order_detailsVO = product_order_detailsSvc.getOneProduct_order_details(prod_ord_no, prod_no);
				if (product_order_detailsVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_order_detailsVO", product_order_detailsVO); 
				String url = "/front-end/product_order_details/listOneProduct_order_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				
				/***************************2.�}�l�d�߸��****************************************/
				Product_order_detailsService product_order_detailsSvc = new Product_order_detailsService();
				Product_order_detailsVO product_order_detailsVO = product_order_detailsSvc.getOneProduct_order_details(prod_ord_no, prod_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("product_order_detailsVO", product_order_detailsVO);        
				String url = "/front-end/product_order_details/update_Product_order_details_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/listAllProduct_order_details.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				Integer prod_amt = new Integer(req.getParameter("prod_amt").trim());
				Integer prod_unit_pc = new Integer(req.getParameter("prod_unit_pc").trim());
			
				Product_order_detailsVO product_order_detailsVO = new Product_order_detailsVO();
				product_order_detailsVO.setProd_ord_no(prod_ord_no);
				product_order_detailsVO.setProd_no(prod_no);
				product_order_detailsVO.setProd_amt(prod_amt);
				product_order_detailsVO.setProd_unit_pc(prod_unit_pc);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_order_detailsVO", product_order_detailsVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/update_Product_order_details_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Product_order_detailsService product_order_detailsSvc = new Product_order_detailsService();
				product_order_detailsVO = product_order_detailsSvc.updateProduct_order_details(prod_ord_no, prod_no, prod_amt, prod_unit_pc);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_order_detailsVO", product_order_detailsVO);
				String url = "/front-end/product_order_details/listOneProduct_order_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/update_Product_order_details_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				Integer prod_amt = new Integer(req.getParameter("prod_amt").trim());
				Integer prod_unit_pc = new Integer(req.getParameter("prod_unit_pc").trim());

				Product_order_detailsVO product_order_detailsVO = new Product_order_detailsVO();
				product_order_detailsVO.setProd_ord_no(prod_ord_no);
				product_order_detailsVO.setProd_no(prod_no);
				product_order_detailsVO.setProd_amt(prod_amt);
				product_order_detailsVO.setProd_unit_pc(prod_unit_pc);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_order_detailsVO", product_order_detailsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/addProduct_order_details.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Product_order_detailsService product_order_detailsSvc = new Product_order_detailsService();
				product_order_detailsVO = product_order_detailsSvc.addProduct_order_details(prod_ord_no, prod_no, prod_amt, prod_unit_pc);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/product_order_details/listAllProduct_order_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct_order_details.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order_details/addProduct_order_details.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				
				/***************************2.�}�l�R�����***************************************/
				Product_order_detailsService product_order_detailsSvc = new Product_order_detailsService();
				product_order_detailsSvc.deleteProduct_order_details(prod_ord_no, prod_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/product_order_details/listAllProduct_order_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order_details/listAllProduct_order_details.jsp");
				failureView.forward(req, res);
			}
		}
	}
}