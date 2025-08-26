using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaPoliticaPrestamos
{
    public class PoliticaPrestamoBO : BaseBO
    {
        public int insertar(int idMoraAsociada, int cantDiasPrestamoRegular,
                            int cantMaxCopiasPorDevolverPorUsuario, int cantDiasDeAmpliacionRegular, int cantMaxAmpliacionesPermitidasPorPrestamo,
                            int cantDiasParaDarPorPerdidaUnaCopia, int cantDiasSinPrestamoPorAtraso, int cantMaxAtrasosPorCiclo,
                            int cantDiasSinPrestamoPorMalEstado, float cargoPorMalEstado, int cantMaxMalEstadoPorCiclo,
                            int cantDiasSinPrestamoPorPerdido, float cargoPorPerdido, int cantMaxPerdidasPorCiclo)
        {
            return this.WsCliente.politicaprestamo_insertar(idMoraAsociada, cantDiasPrestamoRegular, cantMaxCopiasPorDevolverPorUsuario, cantDiasDeAmpliacionRegular, cantMaxAmpliacionesPermitidasPorPrestamo, cantDiasParaDarPorPerdidaUnaCopia, cantDiasSinPrestamoPorAtraso, cantMaxAtrasosPorCiclo, cantDiasSinPrestamoPorMalEstado, cargoPorMalEstado, cantMaxMalEstadoPorCiclo, cantDiasSinPrestamoPorPerdido, cargoPorPerdido, cantMaxPerdidasPorCiclo);
        }

        public int modificar(int idPoliticaPrestamo, int idMoraAsociada, int cantDiasPrestamoRegular,
                            int cantMaxCopiasPorDevolverPorUsuario, int cantDiasDeAmpliacionRegular, int cantMaxAmpliacionesPermitidasPorPrestamo,
                            int cantDiasParaDarPorPerdidaUnaCopia, int cantDiasSinPrestamoPorAtraso, int cantMaxAtrasosPorCiclo,
                            int cantDiasSinPrestamoPorMalEstado, float cargoPorMalEstado, int cantMaxMalEstadoPorCiclo,
                            int cantDiasSinPrestamoPorPerdido, float cargoPorPerdido, int cantMaxPerdidasPorCiclo)
        {
            return this.WsCliente.politicaprestamo_modificar(idPoliticaPrestamo, idMoraAsociada, cantDiasPrestamoRegular, cantMaxCopiasPorDevolverPorUsuario, cantDiasDeAmpliacionRegular, cantMaxAmpliacionesPermitidasPorPrestamo, cantDiasParaDarPorPerdidaUnaCopia, cantDiasSinPrestamoPorAtraso, cantMaxAtrasosPorCiclo, cantDiasSinPrestamoPorMalEstado, cargoPorMalEstado, cantMaxMalEstadoPorCiclo, cantDiasSinPrestamoPorPerdido, cargoPorPerdido, cantMaxPerdidasPorCiclo);
        }

        public int eliminar(int idPoliticaPrestamo)
        {
            return this.WsCliente.politicaprestamo_eliminar(idPoliticaPrestamo);
        }

        public BindingList<politicaPrestamo> listarTodos(int idMoraAsociada, int limite)
        {
            try
            {
                politicaPrestamo[] politicasPrestamo = this.WsCliente.politicaprestamo_listarTodos(idMoraAsociada, limite);
                if (politicasPrestamo == null) return new BindingList<politicaPrestamo>();
                return new BindingList<politicaPrestamo>(politicasPrestamo);
            }
            catch (Exception e)
            {
                return new BindingList<politicaPrestamo>();
            }
        }

        public politicaPrestamo obtenerPorId(int idPoliticaPrestamo)
        {
            return this.WsCliente.politicaprestamo_obtenerPorId(idPoliticaPrestamo);
        }
    }
}
