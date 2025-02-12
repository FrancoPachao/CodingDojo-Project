<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<main class="bg-perfil bg-cover bg-center">
	<!-- Profile Section -->
	<div class="mx-auto flex min-h-[80vh] max-w-screen-2xl flex-col items-start gap-y-4 p-8 md:flex-row md:gap-x-12">
		<div class="mx-auto w-full rounded-xl p-6 shadow-md bg-white dark:bg-neutral-700 dark:text-white">
			<div class="mb-6 text-center">
				<img src="<c:url value='${userInSession.profilePicture}'/>" alt="Foto Perfil" class="mx-auto my-4 size-28 rounded-full bg-slate-100 drop-shadow-[0_4px_4px_rgba(0,0,0,0.25)] object-cover" />
				<h2 class="pb-2 text-4xl font-bold">${userInSession.fullName}</h2>
				<p class="m-2 text-lg font-semibold text-gray-700 dark:text-white">
					<i class="fas fa-phone pr-1"></i> ${userInSession.phoneNumber}
				</p>
			</div>
			<div class="flex flex-col items-center">
				<a href="/editar-perfil" class="mb-2 w-fit rounded-full bg-orange-500 px-10 py-2 text-white hover:bg-orange-700">Editar Perfil</a>
			</div>
		</div>
		<!-- Orders and Publications Section -->
		<div class="mx-auto w-full rounded-xl bg-white dark:bg-neutral-700 p-6 shadow-md">
			<!-- Orders Section -->
			<div class="mb-8">
				<h3 class="mb-2 text-xl font-bold dark:text-white">Órdenes Pendientes:</h3>
				<c:choose>
					<c:when test="${not empty pendingOrders}">
						<c:forEach items="${pendingOrders}" var="po">
							<a href="/ordenes/${po.id}" class="block mb-4 flex gap-x-2 items-center rounded-lg bg-gray-100 p-4 shadow-md dark:bg-neutral-600 hover:scale-[102%]">
								<div class="size-16 rounded-full bg-orange-700 p-1 drop-shadow-[0_4px_4px_rgba(0,0,0,0.25)] md:p-2">
									<img src="<c:url value='/images/iconos/${po.category}s.png'/>" alt="${po.category}" />
								</div>
								<div class="text-gray-800 dark:text-white">
									<p class="font-semibold">¡Solicitaste los servicios de ${po.postOrder.supplier.fullName}!</p>
									<p class="text-xs">
										Fecha estimada:
										<fmt:formatDate value="${po.estimatedDate}" pattern="yyyy-MM-dd" />
									</p>
									<p class="text-sm">${po.postOrder.title}</p>
								</div>
							</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="mb-4 dark:text-white">Por el momento no hay ordenes pendientes.</p>
					</c:otherwise>
				</c:choose>
				<h3 class="mb-2 text-xl font-bold dark:text-white">Órdenes Concretadas:</h3>
				<c:choose>
					<c:when test="${not empty completedOrders}">
						<c:forEach items="${completedOrders}" var="co">
							<div class="mb-4 flex gap-x-2 items-center rounded-lg bg-gray-100 p-4 shadow-md  dark:bg-neutral-600">
								<div class="size-16 rounded-full bg-orange-700 p-1 drop-shadow-[0_4px_4px_rgba(0,0,0,0.25)] md:p-2">
									<img src="<c:url value='/images/iconos/${co.category}s.png'/>" alt="${co.category}" />
								</div>
								<div class="text-gray-800 dark:text-white">
									<p class="font-semibold">¡${co.postOrder.supplier.fullName} realizó un trabajo para vos!</p>
									<p class="text-xs">
										Fecha estimada:
										<fmt:formatDate value="${co.estimatedDate}" pattern="yyyy-MM-dd" />
									</p>
									<p class="text-sm">${co.postOrder.title}</p>
								</div>
								<a href="/ordenes/${co.id}" class="block ml-auto w-fit rounded-full bg-gray-700 px-4 py-2 text-white text-sm hover:bg-gray-800 dark:bg-slate-100 dark:text-gray-800 hover:dark:bg-slate-200">Dejar reseña <i class="fas fa-pencil-alt"></i></a>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="mb-4 dark:text-white">Por el momento no se concretaron ordenes.</p>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- Reviews Section -->
			<div>
				<h3 class="mb-2 text-xl font-bold dark:text-white">Tus Reseñas:</h3>
				<c:choose>
					<c:when test="${not empty reviews}">
						<c:forEach items="${reviews}" var="review">
							<div class="mb-4 flex gap-x-2 items-center rounded-lg bg-gray-100 p-4 shadow-md  dark:bg-neutral-600">
								<div class="size-16 rounded-full bg-orange-700 p-1 drop-shadow-[0_4px_4px_rgba(0,0,0,0.25)] md:p-2">
									<img src="<c:url value='/images/iconos/${review.post.category}s.png'/>" alt="${review.post.category}" />
								</div>
								<div class="text-gray-800 dark:text-white">
									<p class="font-semibold">${review.post.title} <span class="font-normal text-sm">[${review.post.supplier.fullName}]</span></p>
									<img src="<c:url value='/images/home/Puntuacion${review.rating}.png'/>" alt="Puntuación ${review.rating}" class="h-4 w-fit" />
									<p class="text-xs">
										<fmt:formatDate value="${review.createdAt}" pattern="yyyy-MM-dd" />
									</p>
									<p class="text-sm">${review.comentary}</p>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="mb-4 dark:text-white">Todavía no hay reseñas creadas.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</main>