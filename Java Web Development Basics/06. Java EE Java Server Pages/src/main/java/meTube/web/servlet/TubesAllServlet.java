package meTube.web.servlet;

import meTube.domain.models.views.AllTubesVM;
import meTube.service.MeTubeService;
import meTube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/6/2019.
 */
@WebServlet("/tubes/all")
public class TubesAllServlet extends HttpServlet {

    private final MeTubeService service;
    private final ModelMapper modelMapper;

    @Inject
    public TubesAllServlet(MeTubeService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allTubes",this.service.findAllTubes().stream()
        .map(t-> {
            AllTubesVM map = this.modelMapper.map(t, AllTubesVM.class);
            map.setTitle(t.getName());
            return map;
                }
        )
        .collect(Collectors.toList()));

        req.getRequestDispatcher("/jsps/all-tubes.jsp").forward(req,resp);
    }
}
