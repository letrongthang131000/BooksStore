<%-- 
    Document   : history
    Created on : Jul 9, 2021, 8:20:14 PM
    Author     : Thang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>History page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <style>
            #aHeader {
                font-size: 1.75rem;
                color: black;
                width: 80%;
            }
            
            #aLogin {
                width: 20%;
                padding-left: 250px;
            }
            
            #aUser {
                width: 20%;
                color: #F0F8FF;
            }
            
            #cartBtn {
                color: black;
            }
            
            #liUser {
                list-style-type: none;
            }
        </style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${!sessionScope.ADMIN}">
                <nav class="navbar navbar-expand-md" style="background-color: #FF8C00; position: fixed; width: 100%; z-index: 1;">
                    <a class="navbar-brand" href="InitSearchPageController" id="aHeader">The Book Store</a>
                    <c:if test="${empty sessionScope.USER}">
                        <a id="aLogin" class="nav-link" href="login.html">Login</a>
                    </c:if>
                    <c:if test="${not empty sessionScope.USER}">
                        <li id="liUser" class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown" style="color: white;">
                              Welcome, ${sessionScope.USER}
                            </a>
                            <div class="dropdown-menu">
                            <c:if test="${not empty sessionScope.ADMIN}">
                                <c:if test="${!sessionScope.ADMIN}">
                                     <a class="dropdown-item" href="MainController?btAction=History">History</a>
                                </c:if>
                                <a class="dropdown-item" href="MainController?btAction=Logout">Logout</a>
                            </c:if>
                            </div>
                        </li>
                    </c:if>   
                    <c:if test="${not empty sessionScope.ADMIN}">
                        <c:if test="${!sessionScope.ADMIN}">
                            <form action="viewCart.jsp">
                                <button id="cartBtn" class="btn" type="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                    </svg> 
                                </button>
                            </form>
                            <a class="nav-link" href="viewCart.jsp" style="color: black;">Cart</a>
                        </c:if>
                    </c:if>
                  </nav>
                <br/><br/><br/><br/>
                <div class="pl-3" style="font-size: 1.5rem;">
                    History
                </div>
                <br/>
                <div class="pl-3">
                    <form action="MainController" class="form-inline">
                        <input class="form-control mr-sm-2 shadow-sm" 
                            type="text" name="txtSearch" value="${param.txtSearch}"
                            placeholder="Search by name"/> &emsp;
                        <input 
                            class="btn shadow-sm" 
                            style="background-color: #FF8C00; color: #F0F8FF;" 
                            type="submit" name="btAction" value="Search history" />
                    </form> <br/>
                    <form action="MainController" class="form-outline">
                        <p style="opacity: 0.54;">Choose the shopping date</p>
                        <input class="form-control mr-sm-2 shadow-sm" style="width: 15%;" 
                            type="text" name="txtDate" value="${param.txtDate}"  id="datepicker" readonly/> &emsp;
                        <br/>
                        <input 
                            class="btn shadow-sm" 
                            style="background-color: #FF8C00; color: #F0F8FF;" 
                            type="submit" name="btAction" value="Search history" />
                    </form> <br>
                </div>
                <c:set var="items" value="${requestScope.RESULT}" />
                <c:if test="${not empty items}">
                    <div class="pl-3 pr-3">
                        <table class="table table-striped">
                            <thead>
                                <tr style="background-color: #FF8C00;">
                                    <th scope="col">No.</th>
                                    <th scope="col">Order Date</th>
                                    <th scope="col">Order ID</th>
                                    <th scope="col">Book ID</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${items}" varStatus="counter">
                                    <c:if test="${empty tmpOrder}">
                                        <c:set var="tmpOrder" value="${dto}" />
                                    </c:if>
                                    <c:if test="${not empty tmpOrder}">
                                        <c:if test="${tmpOrder.orderID ne dto.orderID}">
                                            <tr style="background-color: #FFF4E7;">
                                                <td></td>
                                                <td>${tmpOrder.orderDay}</td>
                                                <td colspan="5"><strong>TOTAL ORDER ${tmpOrder.orderID}</strong></td>
                                                <td>$${tmpOrder.totalOrder}</td>
                                            </tr>
                                            <c:set var="tmpOrder" value="${dto}" />
                                        </c:if>
                                    </c:if>
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.orderDay}</td>
                                        <td>${dto.orderID}</td>
                                        <td>${dto.bookID}</td>
                                        <td>${dto.title}</td>
                                        <td>${dto.quantity}</td>
                                        <td>$${dto.unitPrice}</td>
                                        <td>$${dto.total}</td>
                                    </tr>
                                    <c:if test="${counter.count eq requestScope.LENGTH}">
                                        <tr style="background-color: #FFF4E7;">
                                            <td></td>
                                            <td>${tmpOrder.orderDay}</td>
                                            <td colspan="5"><strong>TOTAL ORDER ${tmpOrder.orderID}</strong></td>
                                            <td>$${tmpOrder.totalOrder}</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${empty items}">
                    <div class="pl-3" style="font-size: 1.5rem;">
                        No record !
                    </div>
                </c:if>
                <div class="pl-3">
                            <a href="InitSearchPageController" style="font-size: 1rem;">Back to Search Page</a>
                </div>
                <script>
                    $(function () {
                        $("#datepicker").datepicker();
                    });
                </script>
            </c:if>
            <c:if test="${sessionScope.ADMIN}">
                <a href="InitSearchPageController">Back to Search Page</a>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope.USER}">
            <a href="InitSearchPageController">Back to Search Page</a>
        </c:if>
    </body>
</html>
