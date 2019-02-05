package meTube.web.servlet;

import meTube.domain.models.binding.TubeCreateBM;
import meTube.domain.models.service.TubeServiceModel;
import meTube.service.MeTubeService;
import meTube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet {

    private final MeTubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeCreateServlet(MeTubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/create-tube.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBM tubeCreateBM = (TubeCreateBM) req.getAttribute("tubeCreateBM");
        TubeServiceModel tsm = this.modelMapper.map(tubeCreateBM, TubeServiceModel.class);
        tsm.setName(tubeCreateBM.getTitle());
        this.tubeService.saveTube(tsm);

        resp.sendRedirect("/tubes/details?name="+tubeCreateBM.getTitle());
        System.out.println();
    }
}
