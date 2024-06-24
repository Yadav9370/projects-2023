<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Your CSS styles here */
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="biDashboardDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Inventory
                    </a>
                    <div class="dropdown-menu" aria-labelledby="biDashboardDropdown">
                        <a class="dropdown-item" href="inventory.jsp">Insert Item</a>
                        <a class="dropdown-item" href="updates.jsp">Update Item</a>
                        <a class="dropdown-item" href="InventoryDelete.jsp">Delete item</a>
                        <a class="dropdown-item" href="display.jsp">Display Item</a>
                    </div>
                </li>    
                <li class="nav-item">
                    <a class="nav-link" href="sales.jsp">Sales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="customer.jsp">Customer</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="products.jsp">Products</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="biDashboardDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        BI Dashboard
                    </a>
                    <div class="dropdown-menu" aria-labelledby="biDashboardDropdown">
                        <a class="dropdown-item" href="biDashboardOverview.jsp">Inventory levels</a>
                        <a class="dropdown-item" href="biDashboardReports.jsp">Sales Performances</a>
                        <a class="dropdown-item" href="biDashboardAnalytics.jsp">Customer Segmentation</a>
                        <a class="dropdown-item" href="biDashboardAnalytics.jsp">BI Dashboard and Reporting</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="reporting.jsp">Reporting</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <%= session.getAttribute("username") %>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="profile.jsp">Profile</a>
                        <a class="dropdown-item" href="settings.jsp">Settings</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="logout.jsp">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <section id="about" class="container mt-5">
        <h2 class="text-center">About Us</h2>
        <div class="row mt-4 justify-content-center">
            <div class="col-8 text-center">
                <p>
                    LogisticsCo is a leading provider of comprehensive logistics solutions. With years of experience in the industry, we are committed to delivering high-quality services that meet the diverse needs of our clients. Our team of experts works tirelessly to ensure that your goods are transported safely and efficiently, whether it's across the city or around the globe.
                </p>
                <p>
                    Our mission is to provide reliable and innovative logistics solutions that help our clients achieve their business goals. We pride ourselves on our customer-centric approach, leveraging cutting-edge technology and best practices to offer customized solutions that drive success.
                </p>
            </div>
        </div>
    </section>
   
    <section class="container mt-5" id="ourServices">
        <h2 class="text-center">Our Services</h2>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Freight Forwarding</h5>
                        <p class="card-text">We provide efficient and reliable freight forwarding services to 
                            ensure your goods reach their destination safely and on time. Understand the demand.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Warehousing</h5>
                        <p class="card-text">Our state-of-the-art warehousing facilities are equipped with the latest technology to store and manage your inventory effectively.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Customs Brokerage</h5>
                        <p class="card-text">We offer comprehensive customs brokerage services to help you navigate complex import and export regulations with ease.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Supply Chain Management</h5>
                        <p class="card-text">Our supply chain management solutions are designed to optimize your logistics operations and improve efficiency.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Distribution Services</h5>
                        <p class="card-text">We offer reliable distribution services to ensure your products are delivered to your customers on time, every time.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">E-commerce Solutions</h5>
                        <p class="card-text">Our e-commerce logistics solutions are tailored to meet the unique needs of online businesses, providing seamless order fulfillment.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section id="contact" class="container mt-5 mb-5"> 
        <main>
            <div class="container py-5">
              <div class="row g-5">
                <!-- Contact Information Block -->
                <div class="col-xl-6">
                    <div class="contact-info">
                        <div class="row row-cols-md-2 g-4">
                            <div class="col aos-item" data-aos="fade-up" data-aos-delay="200">
                                <div class="bg-light hvr-shutter-out-horizontal d-block p-3">
                                    <div class="d-flex justify-content-start">
                                        <i class="fa-solid fa-envelope h3 pe-2"></i>
                                        <span class="h5">Email</span>
                                    </div>
                                    <span>example@domain.com</span>
                                </div>
                            </div>
                            <div class="col aos-item" data-aos="fade-up" data-aos-delay="400">
                                <div class="bg-light hvr-shutter-out-horizontal d-block p-3">
                                    <div class="d-flex justify-content-start">
                                        <i class="fa-solid fa-phone h3 pe-2"></i>
                                        <span class="h5">Phone</span>
                                    </div>
                                    <span>+0123456789, +9876543210</span>
                                </div>
                            </div>
                        </div>
                        <div class="aos-item mt-4" data-aos="fade-up" data-aos-delay="600">
                            <div class="bg-light hvr-shutter-out-horizontal d-block p-3">
                                <div class="d-flex justify-content-start">
                                    <i class="fa-solid fa-location-pin h3 pe-2"></i>
                                    <span class="h5">Office location</span>
                                </div>
                                <span>#007, Street name, Bigtown BG23 4YZ, England</span>
                            </div>
                        </div>
                        <div class="aos-item" data-aos="fade-up" data-aos-delay="800">
                            <div class="mt-4 w-100 aos-item__inner">
                                <iframe class="hvr-shadow" width="100%" height="345" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?width=100%25&amp;height=300&amp;hl=en&amp;q=1%20Grafton%20Street,%20Dublin,%20Ireland+()&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"><a href="https://www.maps.ie/distance-area-calculator.html">measure acres/hectares on map</a></iframe>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Contact Form Block -->
                <div class="col-xl-6">
                    <h2 class="pb-4">Leave a message</h2>
                    <div class="row g-4">
                        <div class="col-6 mb-3">
                            <label for="exampleFormControlInput1" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="John">
                        </div>
                        <div class="col-6 mb-3">
                            <label for="exampleFormControlInput1" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Doe">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Email</label>
                        <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Phone</label>
                        <input type="tel" class="form-control" id="exampleFormControlInput1" placeholder="+1234567890">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Country</label>
                        <select class="form-select" aria-label="Default select example">
                            <option value="1">India</option>
                            <option value="2">Japan</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">Message</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div>
                    <button type="button" class="btn btn-dark">Send Message</button>
                </div>
              </div>
            </div>
        </main>
    </section>

    <!-- Footer -->
    <footer class="bg-light text-center text-lg-start mt-5">
        <div class="container p-4">
            <div class="row">
                <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                    <h5 class="text-uppercase">LogisticsCo</h5>
                    <p>
                        Providing top-notch logistics services with a focus on reliability and customer satisfaction.
                    </p>
                </div>
                <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                    <h5 class="text-uppercase">Quick Links</h5>
                    <ul class="list-unstyled mb-0">
                        <li>
                            <a href="#" class="text-dark">Home</a>
                        </li>
                        <li>
                            <a href="#" class="text-dark">About Us</a>
                        </li>
                        <li>
                            <a href="#ourServices" class="text-dark">Our Services</a>
                        </li>
                        <li>
                            <a href="#contact" class="text-dark">Contact Us</a>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                    <h5 class="text-uppercase">Contact</h5>
                    <ul class="list-unstyled mb-0">
                        <li>
                            <a href="mailto:info@logisticsco.com" class="text-dark">info@logisticsco.com</a>
                        </li>
                        <li>
                            <a href="tel:+1234567890" class="text-dark">+1 234 567 890</a>
                        </li>
                        <li>
                            <p class="text-dark mb-0">1234 Main St, Anytown, USA</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="text-center p-3 bg-dark text-white">
            &copy; 2024 LogisticsCo. All rights reserved.
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
