<%@ Page Title="Catálogo" Language="C#" MasterPageFile="~/SoftBibliotecaUsuario.Master" AutoEventWireup="true" CodeBehind="Catálogo.aspx.cs" Inherits="SoftBibliotecaWA.Catalogo" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">

    <div class="container-fluid" > 
        <div class="row" > 
            <div class="col-md-3 "  > 
                <div class="sidebar">
                    <h5>Filtros</h5>
                    
                    <div class="mb-3">
                        <label for="ddlUniversidad" class="form-label">Universidad</label>
                        <asp:DropDownList ID="ddlUniversidad" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione una universidad" Value=""></asp:ListItem>
                            <asp:ListItem Text="Universidad 1" Value="1"></asp:ListItem>
                            <asp:ListItem Text="Universidad 2" Value="2"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                    
                    <div class="mb-3">
                        <label for="ddlTipo" class="form-label">Tipo de Libro</label>
                        <asp:DropDownList ID="ddlTipo" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione un tipo" Value=""></asp:ListItem>
                            <asp:ListItem Text="Libro" Value="libro"></asp:ListItem>
                            <asp:ListItem Text="Revista" Value="revista"></asp:ListItem>
                            <asp:ListItem Text="Tesis" Value="tesis"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                    
                    <button type="button" class="btn btn-primary w-100">Aplicar Filtros</button>
                </div>
            </div>

            <div class="col-md-6" style="padding-left: 0;"> <!-- Eliminamos el padding izquierdo -->
                <div class="content">
                    <div class="mb-4">
                        <div class="input-group">
                            <input type="text" class="form-control" id="searchBar" placeholder="Buscar por título, autor, etc.">
                            <button type="button" class="btn btn-primary">Buscar</button>
                        </div>
                         <a href="BusquedaAvanzada.aspx" class="btn btn-secondary">Búsqueda Avanzada</a>
                    </div>
                    
                    <div id="bookContainer" class="book-list">
                        <!-- Aquí se mostrarán los libros después de la conexión a la base de datos -->
                        <div class="book-card">Libro 1</div>
                        <div class="book-card">Libro 2</div>
                        <div class="book-card">Libro 3</div>
                        <div class="book-card">Libro 4</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
