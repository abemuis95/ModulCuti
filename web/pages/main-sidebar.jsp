<%-- 
    Document   : main-sidebar.jsp
    Created on : Dec 11, 2016, 2:16:30 PM
    Author     : USER
--%>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<c:out value='${sessionScope.staffSession.photo}'/>" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><c:out value="${sessionScope.staffSession.name}"/></p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>eCuti Dashboard</span>
                </a>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>Cuti</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-angle-double-right"></i> Cuti Rehat
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="MohonCuti"><i class="fa fa-circle-o"></i> Mohon Cuti Rehat</a></li>
                            <li><a href="SejarahPermohonan"><i class="fa fa-circle-o"></i> Sejarah Permohonan</a></li>
                            <li><a href="BatalCuti"><i class="fa fa-circle-o"></i> Batal Permohonan</a></li>
                            <li><a href="SokongLulus"><i class="fa fa-circle-o"></i> Sokong/Lulus Permohonan</a></li>
                        </ul>
                    </li>

                    <li><a href="#"><i class="fa fa-angle-double-right"></i> Cuti Sakit</a></li>
                    <li><a href="#"><i class="fa fa-angle-double-right"></i> Cuti Lahir</a></li>
                </ul>
            </li>
            <% //when user is admin show this menu list on dashboard %>
            <c:choose>
                <c:when test="${sessionScope.staffSession.role == 1}" >
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-edit"></i> <span>Admin</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-angle-double-right"></i> Cuti
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="daftarCuti.html"><i class="fa fa-circle-o"></i> Daftar Cuti</a></li>
                                    <li><a href="hapusCuti.html"><i class="fa fa-circle-o"></i> Hapus Cuti</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </c:when>
            </c:choose>
            <li class="treeview">
                <a href="Logout">
                    <i class="fa fa-sign-out"></i> <span>Logout</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>