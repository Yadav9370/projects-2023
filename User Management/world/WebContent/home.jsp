<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .hero {
            background: url('hero-image.jpg') no-repeat center center/cover;
            color: white;
            padding: 100px 0;
            text-align: center;
        }

        .hero h1 {
            font-size: 3rem;
        }

        .hero p {
            font-size: 1.5rem;
        }

        .features, .about, .services, .testimonials, .blog, .contact {
            padding: 60px 0;
        }

        .features .col-md-4, .testimonials .col-md-4 {
            margin-bottom: 20px;
        }

        .footer {
            background-color: #f8f9fa;
            padding: 20px 0;
            text-align: center;
        }

        .footer p {
            margin-bottom: 0;
        }

        .footer ul {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
            <img src="logo.png" alt="Logo" width="30" height="30" class="d-inline-block align-top">
            Logo
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#about">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#services">Services</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#blog">Blog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#contact">Contact</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-primary" href="#">Get Started</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <h1 style="color:black;">Welcome to Our Website</h1>
            <p style="color:black;">Your success is our priority.</p>
            <a href="#" class="btn btn-primary btn-lg">Get Started</a>
        </div>
    </section>

    <!-- Features Section -->
    <section class="features">
        <div class="container">
            <div class="row">
                <div class="col-md-4 text-center">
                    <img src="feature1-icon.png" alt="Feature One" class="img-fluid mb-3">
                    <h3>Feature One</h3>
                    <p>Description of feature one.</p>
                </div>
                <div class="col-md-4 text-center">
                    <img src="feature2-icon.png" alt="Feature Two" class="img-fluid mb-3">
                    <h3>Feature Two</h3>
                    <p>Description of feature two.</p>
                </div>
                <div class="col-md-4 text-center">
                    <img src="feature3-icon.png" alt="Feature Three" class="img-fluid mb-3">
                    <h3>Feature Three</h3>
                    <p>Description of feature three.</p>
                </div>
            </div>
        </div>
    </section>

    <!-- About Section -->
    <section id="about" class="about">
        <div class="container">
            <h2>About Us</h2>
            <img src="about-image.jpg" alt="About Us" class="img-fluid mb-3">
            <p>We are a company dedicated to providing the best services to our clients.</p>
            <a href="#" class="btn btn-primary">Learn More</a>
        </div>
    </section>

    <!-- Services Section -->
    <section id="services" class="services">
        <div class="container">
            <h2>Our Services</h2>
            <div class="row">
                <div class="col-md-4">
                    <img src="service1-image.jpg" alt="Service One" class="img-fluid mb-3">
                    <h3>Service One</h3>
                    <p>Description of service one.</p>
                    <a href="#" class="btn btn-primary">Learn More</a>
                </div>
                <div class="col-md-4">
                    <img src="service2-image.jpg" alt="Service Two" class="img-fluid mb-3">
                    <h3>Service Two</h3>
                    <p>Description of service two.</p>
                    <a href="#" class="btn btn-primary">Learn More</a>
                </div>
                <div class="col-md-4">
                    <img src="service3-image.jpg" alt="Service Three" class="img-fluid mb-3">
                    <h3>Service Three</h3>
                    <p>Description of service three.</p>
                    <a href="#" class="btn btn-primary">Learn More</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Testimonials Section -->
    <section class="testimonials">
        <div class="container">
            <h2>What Our Clients Say</h2>
            <div class="row">
                <div class="col-md-4">
                    <img src="client1-image.jpg" alt="Client One" class="img-fluid mb-3">
                    <p>"Great service!"</p>
                    <p>- Client One</p>
                </div>
                <div class="col-md-4">
                    <img src="client2-image.jpg" alt="Client Two" class="img-fluid mb-3">
                    <p>"Excellent support!"</p>
                    <p>- Client Two</p>
                </div>
                <div class="col-md-4">
                    <img src="client3-image.jpg" alt="Client Three" class="img-fluid mb-3">
                    <p>"Highly recommend!"</p>
                    <p>- Client Three</p>
                </div>
            </div>
        </div>
    </section>

    <!-- Blog Section -->
    <section id="blog" class="blog">
        <div class="container">
            <h2>Latest Blog Posts</h2>
            <div class="row">
                <div class="col-md-4">
                    <img src="post1-image.jpg" alt="Post One" class="img-fluid mb-3">
                    <h3>Post One</h3>
                    <p>Excerpt of post one.</p>
                    <a href="#" class="btn btn-primary">Read More</a>
                </div>
                <div class="col-md-4">
                    <img src="post2-image.jpg" alt="Post Two" class="img-fluid mb-3">
                    <h3>Post Two</h3>
                    <p>Excerpt of post two.</p>
                    <a href="#" class="btn btn-primary">Read More</a>
                </div>
                <div class="col-md-4">
                    <img src="post3-image.jpg" alt="Post Three" class="img-fluid mb-3">
                    <h3>Post Three</h3>
                    <p>Excerpt of post three.</p>
                    <a href="#" class="btn btn-primary">Read More</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact" class="contact">
        <div class="container">
            <h2>Contact Us</h2>
            <form>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" required>
                </div>
                <div class="form-group">
                    <label for="message">Message</label>
                    <textarea class="form-control" id="message" rows="3" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Send Message</button>
            </form>
        </div>
    </section>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <p>&copy; 2023 Your Company. All rights reserved.</p>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
                <li class="list-inline-item"><a href="#">Terms of Service</a></li>
            </ul>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Example JavaScript code
            console.log('Homepage loaded');

            // Add any additional JavaScript functionality here
        });
    </script>
</body>
</html>
