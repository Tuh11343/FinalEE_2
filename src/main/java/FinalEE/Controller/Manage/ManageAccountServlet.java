package FinalEE.Controller.Manage;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageAccountServlet extends HttpServlet {

    private AccountServiceImpl accountServiceImpl;
    private CustomerServiceImpl customerServiceImpl;

    private PermissionServiceImpl permissionServiceImpl;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        accountServiceImpl = (AccountServiceImpl) servletContext.getAttribute("accountServiceImpl");
        customerServiceImpl = (CustomerServiceImpl) servletContext.getAttribute("customerServiceImpl");
        permissionServiceImpl = (PermissionServiceImpl) servletContext.getAttribute("permissionServiceImpl");

        List<Account> accountList = accountServiceImpl.getAllAccount();
        List<Customer> customerList=customerServiceImpl.getAllCustomer();
        List<Permission> permissionList=permissionServiceImpl.getAllPermission();

        /*Set Data List*/
        req.setAttribute("accountList", accountList);
        req.setAttribute("customerList", customerList);
        req.setAttribute("permissionList", permissionList);


        req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            /*Account*/
            case "deleteAccount" -> {
                int accountID = Integer.parseInt(req.getParameter("accountID"));
                if (accountServiceImpl.deleteByID(accountID)) {
                    resp.getWriter().println("<script>alert('Xóa tài khoản thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa tài khoản thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManageAccountServlet");
            }
            case "updateAccount" -> {

                int id = Integer.parseInt(req.getParameter("update_accountID"));
                String name = req.getParameter("update_accountName");
                String password = req.getParameter("update_accountPassword");
                int permissionID = Integer.parseInt(req.getParameter("update_accountPermissionID"));
                int customerID = Integer.parseInt(req.getParameter("update_accountCustomerID"));

                Permission permission = permissionServiceImpl.getPermission(permissionID);
                Customer customer = customerServiceImpl.getCustomer(customerID);

                Account account = new Account();
                account.setId(id);
                account.setName(name);
                account.setCustomer(customer);
                account.setPassword(password);
                account.setPermission(permission);

                if (accountServiceImpl.create(account)) {
                    resp.getWriter().println("<script>alert('Cập nhật tài khoản thành công!');</script>");

                } else {
                    resp.getWriter().println("<script>alert('Cập nhật tài khoản thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageAccountServlet");

            }
            case "addAccount" -> {

                String name = req.getParameter("add_accountName");
                String password = req.getParameter("add_accountPassword");
                int permissionID = Integer.parseInt(req.getParameter("add_accountPermissionID"));
                int customerID = Integer.parseInt(req.getParameter("add_accountCustomerID"));

                Customer customer = customerServiceImpl.getCustomer(customerID);
                Permission permission = permissionServiceImpl.getPermission(permissionID);

                Account account = new Account();
                account.setName(name);
                account.setCustomer(customer);
                account.setPassword(password);
                account.setPermission(permission);

                if (accountServiceImpl.create(account)) {
                    resp.getWriter().println("<script>alert('Thêm tài khoản thành công!');</script>");

                } else {
                    resp.getWriter().println("<script>alert('Thêm tài khoản thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageAccountServlet");
            }
            case "searchAndSortAccount"->{
                String searchType = req.getParameter("accountSearchType");
                String accountInputSearch = req.getParameter("accountInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer accountID = Integer.parseInt(req.getParameter("accountInputSearch"));
                        Account account = accountServiceImpl.getAccount(accountID);
                        List<Account> accountList = new ArrayList<>();
                        accountList.add(account);

                        req.setAttribute("accountList", accountList);
                        req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);

                    }
                    case "name" -> {
                        String accountSortType = req.getParameter("accountSortType");
                        List<Account> accountList = null;
                        if (accountSortType.equals("az")) {
                            accountList = accountServiceImpl.findAllByNameContainsSort(accountInputSearch, "name", ItemServiceImpl.SortOrder.ASC);
                        } else if (accountSortType.equals("za")) {
                            accountList = accountServiceImpl.findAllByNameContainsSort(accountInputSearch, "name", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("accountList", accountList);
                        req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);
                    }
                    case "customerID" -> {
                        Integer customerID = Integer.parseInt(req.getParameter("accountInputSearch"));
                        Account account = accountServiceImpl.findByCustomerID(customerID);
                        List<Account> accountList = new ArrayList<>();
                        accountList.add(account);

                        req.setAttribute("accountList", accountList);
                        req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);
                    }
                }
            }
            case "refreshAccount"->{
                List<Account> accountList=accountServiceImpl.getAllAccount();
                req.setAttribute("accountList", accountList);
                System.out.println(accountList.size());
                req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);
            }
        }
    }
}
