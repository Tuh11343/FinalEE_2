package FinalEE.Controller;

import FinalEE.ServiceImpl.StatisticServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StatisticServlet extends HttpServlet {

    private StatisticServiceImpl statisticServiceImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext=getServletContext();

        statisticServiceImpl = (StatisticServiceImpl) servletContext.getAttribute("statisticServiceImpl");
        List<Object[]> test=statisticServiceImpl.productsSoldByMonth(11);
        System.out.println("Test So Luong:"+test.size());

        String requestedWith = req.getHeader("X-Requested-With");
        if (requestedWith != null && requestedWith.equals("XMLHttpRequest")){
            String action=req.getParameter("action");

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            JSONObject jsonResponse=new JSONObject();
            switch (action){

                case "test"->{
                    try{
                        jsonResponse.put("test",test);
                        out.print(jsonResponse);
                    }catch (Exception er){
                        er.printStackTrace();
                    }
                }

                default -> {

                }
            }

        }



        req.getRequestDispatcher("Views/User/QuanLy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        switch (action){

            case "productsSoldByMonth"->{

            }
            case "productsSoldByYear"->{

            }
            case "revenue"->{

            }

            default -> {

            }
        }
    }
}
