using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using SoftBibliotecaBO.Reportes;
using SoftBibliotecaBO.ServicioWeb;

namespace SoftBibliotecaBO
{
    public class BaseBO
    {
        private ServicioWeb.ServicioWebClient wsCliente;
        private Reportes.ReportesClient wsReporte;
        public BaseBO()
        {
            this.WsCliente = new ServicioWeb.ServicioWebClient();
            this.WsReporte = new Reportes.ReportesClient();
        }

        protected ServicioWebClient WsCliente { get => wsCliente; set => wsCliente = value; }
        protected ReportesClient WsReporte { get => wsReporte; set => wsReporte = value; }

        public void abrirReporte(HttpResponse response, string nombreDeReporte, byte[] reporte)
        {
            response.Clear();
            response.ContentType = "application/pdf";
            response.AddHeader("Content-Disposition", "inline;filename=" + nombreDeReporte + ".pdf");
            response.BinaryWrite(reporte);
            response.End();
        }
    }
}
