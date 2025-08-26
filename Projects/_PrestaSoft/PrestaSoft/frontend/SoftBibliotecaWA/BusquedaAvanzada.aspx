<%@ Page Title="" Language="C#" MasterPageFile="~/SoftBibliotecaUsuario.Master" AutoEventWireup="true" CodeBehind="BusquedaAvanzada.aspx.cs" Inherits="SoftBibliotecaWA.BusquedaAvanzada" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Búsqueda Avanzada de publicaciones
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <h3>Búsqueda avanzada de publicaciones</h3>
    <h5>Dejar en blanco para valor genérico</h5>
    <div class="mb-3 row">
        <label for="txtNombre" class="col-sm-2 col-form-label">Nombre</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="txtNombre" placeholder="Ingrese nombre de la publicación" required>
        </div>
    </div>

    <div class="mb-3 row">
        <label for="txtDesc" class="col-sm-2 col-form-label">Descripcion</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="txtDesc" placeholder="Ingrese descripcion (comodín)" required>
        </div>
    </div>

    <div class="mb-3 row">
        <label for="ddlAutor" class="col-sm-2 col-form-label">Autor(es)</label>
        <div class="col-sm-9">
            <asp:DropDownList ID="DropDownListAutor" runat="server" CssClass="form-select" >
                <asp:ListItem Text="Seleccione un autor" Value=""></asp:ListItem>
            </asp:DropDownList>
        </div>
        <div class="col-sm-1">
            <asp:Button ID="Button1" runat="server" Text="+" CssClass="btn btn-secondary" OnClick="btnAgregarAutor_Click" />
        </div>
    </div>

    <div class="mb-3 row">
        <label for="ddlTipo" class="col-sm-2 col-form-label">Tipo</label>
        <div class="col-sm-9">
            <asp:DropDownList ID="DropDownListTipo" runat="server" CssClass="form-select" >
                <asp:ListItem Text="Seleccione un tipo de publicación" Value=""></asp:ListItem>
            </asp:DropDownList>
        </div>
    </div>

      <div class="mb-3 row">
          <label for="ddlTipoLibro" class="col-sm-2 col-form-label">Tipo de libro</label>
          <div class="col-sm-9">
              <asp:DropDownList ID="DropDownListTipoLibro" runat="server" CssClass="form-select" >
                  <asp:ListItem Text="Seleccione un tipo de libro" Value=""></asp:ListItem>
              </asp:DropDownList>
          </div>
      </div>


    <div class="mb-3 row">
        <label for="ddlEditorial" class="col-sm-2 col-form-label">Editorial</label>
        <div class="col-sm-9">
            <asp:DropDownList ID="DropDownListEditorial" runat="server" CssClass="form-select" >
                <asp:ListItem Text="Seleccione una editorial" Value=""></asp:ListItem>
            </asp:DropDownList>
        </div>
    </div>

  

    <div class="mb-3 row">
        <label for="ddlTipoGrado" class="col-sm-2 col-form-label">Tipo de grado</label>
        <div class="col-sm-9">
            <asp:DropDownList ID="DropDownListTipoGrado" runat="server" CssClass="form-select" >
                <asp:ListItem Text="Seleccione un tipo de grado" Value=""></asp:ListItem>
            </asp:DropDownList>
        </div>
    </div>

    <div class="mb-3 row">
        <label for="ddlTema" class="col-sm-2 col-form-label">Tema(s)</label>
        <div class="col-sm-9">
            <asp:DropDownList ID="DropDownListTema" runat="server" CssClass="form-select" >
                <asp:ListItem Text="Seleccione un tema" Value=""></asp:ListItem>
            </asp:DropDownList>
        </div>
        <div class="col-sm-1">
            <asp:LinkButton ID="btnTema" runat="server" Text="+" CssClass="btn btn-secondary" OnClick="btnTema_Click" />
        </div>
    </div>

    <div class="mb-3 row">
        <label class="col-sm-2 col-form-label">Tiene formato digital</label>
        <div class="col-sm-10">
            <asp:CheckBox ID="chkFormatoDigital" runat="server" Text="Sí" />
        </div>
    </div>

    <div class="mb-3 row">
        <div class="col-sm-10 offset-sm-2">
            <button type="submit" class="btn btn-primary">Buscar publicación</button>
            <asp:LinkButton ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="btnCancelar_Click" />
        </div>
    </div>



</asp:Content>
