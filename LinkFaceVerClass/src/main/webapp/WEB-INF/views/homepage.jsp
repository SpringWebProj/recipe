<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ include file="includes/header.jsp" %>
		<!-- <div class="bi-star-fill"></div>  별점 표시 -->
        <!-- Section-->

        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
            	<h1>베스트 요리</h1>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach items="${food}" var="foods">
                		<div class="col mb-5">
                        	<div class="card h-100">
								<input type="text" name="recipeId" value="${foods.recipeid}" />
	                            <img class="card-img-top" src="${foods.imgurl}" alt="..." />
	                            <div class="card-body p-4">
    	                            <div class="text-center">
	                                    <h5 class="fw-bolder">${foods.recipenmko}</h5>
	                                </div>
    	                        </div>
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
    	                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">보러가기</a><sec:authorize access="isAuthenticated()"><button name="jjimbtn" class="btn btn-outline-dark mt-auto" >찜</button></sec:authorize></div>
        	                    </div>
            	            </div>
                	    </div>
					</c:forEach>
                </div>
            </div>
        </section>

		<script type="text/javascript" src="/resources/js/ajax.js">

		</script>
		<script>
		$(document).ready(function(){
			let csrfHeader = `${_csrf.headerName}`;
			let csrfToken = `${_csrf.token}`;
			let url="";
			console.log(csrfHeader);
			console.log(csrfToken);
			let urlc= ${jjims};
			function jjimajax(recipe,url,csrfHeader,csrfToken){
				  $.ajax({
				    type: 'POST',
				    dataType: 'json',
				    data : JSON.stringify(recipe),
				    url: url,
				    contentType: 'application/json; charset-utf-8',
				    beforeSend : function(xmlHttpRequest){
				      xmlHttpRequest.setRequestHeader(csrfHeader,csrfToken);
				    },
				    success : function(res){
				    	urlc=res;
				      	a(res);
				    },
				    error : function(error){
				      console.log(error);
				    }

				  })
				}

			const a = (res) =>{
				console.log(res);	
				$("button[name='jjimbtn']").each(function(){
					$(this).css("background-color","white");
					for(jjim of res){
						if(jjim == $(this).parents('div').eq(2).children("input[name='recipeId']").val()){
							$(this).attr("style", "background-color:red;")
						}
					}
				});
			}
			$("button[name='jjimbtn']").each(function(){
					for(jjim of ${jjims}){
						if(jjim == $(this).parents('div').eq(2).children("input[name='recipeId']").val()){
							$(this).attr("style", "background-color:red;")
						}
					}
				});
			
			$("button[name='jjimbtn']").click(function(){
				let recipeId=$(this).parents('div').eq(2).children("input[name='recipeId']").val();
				url="/regjjim"
				for(jjim of urlc){
					if(jjim != recipeId){
						url="/regjjim";
					}
					else{
						url="/deljjim";
						break;
					}
				}
				let data = { userKey:"", jjim : recipeId , foodingredients:""};
				jjimajax(data,url,csrfHeader,csrfToken);
				console.log(data);

			})
			
		})
		</script>
		<%@ include file="includes/footer.jsp" %>