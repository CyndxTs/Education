<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="~/ReportesBibliotecario.aspx.cs" Inherits="SoftBibliotecaWA.ReportesBibliotecario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="d-flex justify-content-center align-items-center" style="height: 100vh; flex-direction: column;">
        <!-- Contenedor de la carpeta -->
        <div class="folder-container">
            <!-- Pestañas tipo folder -->
            <ul class="nav nav-tabs folder-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="tab1-tab" data-bs-toggle="tab" data-bs-target="#tab1" type="button" role="tab" aria-controls="tab1" aria-selected="true">
                        Miembros
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="tab2-tab" data-bs-toggle="tab" data-bs-target="#tab2" type="button" role="tab" aria-controls="tab2" aria-selected="false">
                        Publicaciones
                    </button>
                </li>
            </ul>

            <!-- Contenido dentro de la carpeta -->
            <div class="folder-content tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="tab1" role="tabpanel" aria-labelledby="tab1-tab">
                    <!-- Contenido de la primera pestaña -->
                    <h2 class="text-center mb-4">Reporte de Miembros</h2>
                    <div class="mb-3 left-aligned">
                        <label for="txtCantidadSanciones" class="form-label">Cantidad de sanciones</label>
                        <div class="input-group">
                            <asp:TextBox ID="txtCantidadSancionesMin" CssClass="form-control" runat="server" placeholder="Min"></asp:TextBox>
                            <span class="input-group-text">a</span>
                            <asp:TextBox ID="txtCantidadSancionesMax" CssClass="form-control" runat="server" placeholder="Max"></asp:TextBox>
                        </div>
                    </div>
                    <div class="mb-3 left-aligned">
                    <label for="txtFechaRegistro" class="form-label">Fecha de registro</label>
                        <div class="input-group">
                            <asp:TextBox ID="txtFechaRegistroDesde" CssClass="form-control" runat="server" placeholder="Desde" TextMode="Date"></asp:TextBox>
                            <span class="input-group-text">a</span>
                            <asp:TextBox ID="txtFechaRegistroHasta" CssClass="form-control" runat="server" placeholder="Hasta" TextMode="Date"></asp:TextBox>
                        </div>
                    </div>
                    <div class="mb-3 left-aligned">
                        <asp:CheckBox ID="chkMostrarReservas" CssClass="form-check-input" runat="server" />
                        <label for="chkMostrarReservas" class="form-label">Incluir reservas</label>
                    </div>
                    <div class="mb-3 left-aligned">
                        <label for="ddlUniversidad" class="form-label">Universidad</label>
                        <asp:DropDownList ID="ddlUniversidad" CssClass="form-select" runat="server">
                            
                            
                        </asp:DropDownList>
                    </div>
                    <!-- Botón para descargar informe -->
                    <div class="text-center">
                        <asp:Button ID="btnDescargarReporteMiembros" CssClass="btn btn-primary w-100" runat="server" Text="Descargar reporte" OnClick="btnDescargarReporteMiembros_Click"/>
                    </div>
                </div>

                <div class="tab-pane fade" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
                    <!-- Contenido de la segunda pestaña -->
            <div class="folder-content tab-content" id="myTabContentPublicaciones">
                <div class="tab-pane fade show active" id="tab3" role="tabpanel" aria-labelledby="tab3-tab">
                    
                    <h2 class="text-center mb-4">Reporte de Publicaciones</h2>
                    <div class="mb-3 left-aligned">
                        <label for="txtCantidadSanciones" class="form-label">Cantidad de préstamos</label>
                        <div class="input-group">
                            <asp:TextBox ID="txtNumberMin" CssClass="form-control" runat="server" placeholder="Min"></asp:TextBox>
                            <span class="input-group-text">a</span>
                            <asp:TextBox ID="txtNumberMax" CssClass="form-control" runat="server" placeholder="Max"></asp:TextBox>
                        </div>
                    </div>
 
                    <div class="mb-3 left-aligned">
                        <asp:CheckBox ID="CheckBox1" CssClass="form-check-input" runat="server" />
                        <label for="CheckBox1" class="form-label">Incluir publicaciones que solo tienen formato físico</label>
                    </div>
                    
                    <div class="mb-3 left-aligned">                        
                        <label for="ddlUniversidadPublicaciones" class="form-label">Universidad</label>
                            <asp:DropDownList ID="ddlUniversidadPublicaciones" CssClass="form-select" runat="server">
    
    
                            </asp:DropDownList>
                    </div>
                    <!-- Botón para descargar informe -->
                    <div class="text-center">
                        <asp:Button ID="btnDescargarReportePublicaciones" CssClass="btn btn-primary w-100" runat="server" Text="Descargar reporte" OnClick="btnDescargarReportePublicaciones_Click"/>
    
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>

 <style>
    /* Contenedor general del folder */
    .folder-container {
        background-color: #f8e8c0; /* Fondo color manila */
        border: 1px solid #d6c6a3; /* Borde más oscuro */
        border-radius: 5px; /* Bordes redondeados */
        width: 100%;
        max-width: 800px;
        padding: 1rem;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    /* Pestañas tipo folder */
    .folder-tabs {
        margin-bottom: -1px; /* Superpone las pestañas al contenedor */
        border-bottom: none;
    }

    .folder-tabs .nav-link {
        color: #000000 !important;  /* Texto negro */
        background-color: transparent !important; /* Fondo transparente para coincidir con el fondo manila */
        border: 1px solid transparent;
        border-radius: 5px 5px 0 0;
        padding: 0.5rem 1rem;
    }

    .folder-tabs .nav-link.active {
        background-color: #f8e8c0; /* Fondo manila para pestaña activa */
        border-color: #d6c6a3 #d6c6a3 transparent; /* Bordes visibles */
        color: #000000; /* Texto negro */
        font-weight: bold;
    }

    .folder-tabs .nav-link:hover {
        background-color: #f4deb3; /* Fondo más claro al pasar el cursor */
        color: #000000; /* Texto negro */
    }

    /* Contenido dentro de la carpeta */
    .folder-content {
        padding: 1rem;
        background-color: #f8e8c0; /* Fondo manila para el contenido */
        border-top: 1px solid #d6c6a3;
        border-radius: 0 0 5px 5px; /* Bordes inferiores redondeados */
    }

    /* Ajustes para el título */
    h2 {
        color: #4a3c31; /* Marrón oscuro */
    }
</style>


</asp:Content>
