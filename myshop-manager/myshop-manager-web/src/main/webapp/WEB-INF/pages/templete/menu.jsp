<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                    <!-- /input-group -->
                </li>
                <li>
                    <a href="index.html"><i class="fa fa-dashboard fa-fw"></i> 个人工作台</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 商品管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/items/additem.html">添加商品</a>
                        </li>
                        <li>
                            <a href="/items/itemList.html">商品列表</a>
                        </li>
                        <li>
                            <a href="/items/itemsCategory.html">商品类别列表</a>
                        </li>
                        <li>
                             <a href="/items/itemsSpec.html">商品规格设置</a>
                         </li>
                        <li>
                            <a href="/items/itemsBrands.html">商品品牌管理</a>
                        </li>
                        
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="tables.html"><i class="fa fa-users fa-fw"></i> 会员管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                    	 <li>
                             <a href="../member/listMembers.html">会员查询</a>
                         </li>
                         <li>
                             <a href="../member/listMemberPoints.html">积分管理</a>
                         </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="forms.html"><i class="fa fa-user-md fa-fw"></i> 员工管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="listEmployees.html">员工查询</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-list-alt  fa-fw"></i> 订单管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="morris.html">会员查询</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="#"><i class="fa fa-shopping-cart  fa-fw"></i> 供货商管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="morris.html">会员查询</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li class="active">
                     <a href="#"><i class="fa fa-bar-chart-o  fa-fw"></i> 销售报表<span class="fa arrow"></span></a>
                     <ul class="nav nav-second-level">
                         <li>
                             <a href="morris.html">会员查询</a>
                         </li>
                     </ul>
                     <!-- /.nav-second-level -->
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
</nav>
<!-- /.Navigation -->