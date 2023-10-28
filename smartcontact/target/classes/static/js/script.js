{/* <script src="https://checkout.razorpay.com/v1/checkout.js"></script> */}

// const paymentStart = () => {
//     console.log("payment started...");
//     let amt = 5000;
//     $.ajax({
//         url: '/course/create_order',
//         data: JSON.stringify({ amount: amt, info: 'order_request' }),
//         contentType: 'application/json',
//         type: 'POST',
//         dataType: 'json',
//         beforeSend: function () {
//             console.log("Before sending the AJAX request");
//         },
//         success: function (response) {
//             console.log("AJAX request successful");
//             console.log(response);

//             if (response.status == "created") {
//                 let options = {
//                     "key": "rzp_test_9hNNOxDulFsvlJ",
//                     "amount": response.amount,
//                     "currency": "INR",
//                     "name": "E-Courses",
//                     "description": "Course Payment",
//                     "image": "https://cdn-icons-png.flaticon.com/512/1019/1019607.png",
//                     "order_id": response.id,
//                     "handler": function (response) {
//                         console.log("Payment handler function called");
//                         console.log(response.razorpay_payment_id);
//                         console.log(response.razorpay_order_id);
//                         console.log(response.razorpay_signature);
//                         console.log('Payment Successful!');
//                         alert("Congratulations, payment successful.");
//                     },
//                     "prefill": {
//                         "name": "",
//                         "email": "",
//                         "contact": ""
//                     },
//                     "notes": {
//                         "address": "E-Courses Corporate Office"
//                     },
//                     "theme": {
//                         "color": "#3399cc"
//                     }
//                 };
//                 let rzp = new Razorpay(options);
//                 rzp.on('payment.failed', function (response) {
//                     console.log("Payment failed handler function called");
//                     console.log(response.error.code);
//                     console.log(response.error.description);
//                     console.log(response.error.source);
//                     console.log(response.error.step);
//                     console.log(response.error.reason);
//                     console.log(response.error.metadata.order_id);
//                     console.log(response.error.metadata.payment_id);
//                     alert("Oops! Payment Failed!");
//                 });
//                 rzp.open();
//             }
//         },
//         error: function (error) {
//             console.log("Error detected");
//             console.log("AJAX Error: " + error.status);
//             console.log("Status: " + error.statusText);
//             console.log(error.responseText); // Log the error response text
//             alert("AJAX Error: " + error.status);
//         }
        
//     });
// };

function openRazorpayCheckout(razorpayKey, subjectId) {
    // Fetch user details from your backend using an appropriate method.
    const userName = "John Doe"; // Replace with the actual user's name.
    const userEmail = "john@example.com"; // Replace with the actual user's email.
    const userContact = "1234567890"; // Replace with the actual user's contact number.

    const options = {
        key: razorpayKey,
        amount: 5000, // Amount in paise or the smallest currency unit
        currency: 'INR',
        name: 'E-Courses',
        description: 'Course Payment',
        image: 'https://cdn-icons-png.flaticon.com/512/1019/1019607.png',
        prefill: {
            name: userName,
            email: userEmail,
            contact: userContact,
        },
        notes: {
            address: 'E-Courses Corporate Office',
        },
        theme: {
            color: '#3399cc',
        },
    };

    const rzp = new Razorpay(options);

    rzp.on('payment.success', function (response) {
        console.log("Payment success handler function called");
        console.log(response.razorpay_payment_id);
        console.log(response.razorpay_order_id);
        console.log(response.razorpay_signature);
        // alert("Congratulations, payment successful.");

        // After successful payment, find the associated form and submit it
        const form = $('button[data-subject-id="' + subjectId + '"]').closest('form');
        if (form) {
            form.submit();
        }
    });

    rzp.on('payment.failed', function (response) {
        console.log("Payment failed handler function called");
        console.log(response.error.code);
        console.log(response.error.description);
        console.log(response.error.source);
        console.log(response.error.step);
        console.log(response.error.reason);
        console.log(response.error.metadata.order_id);
        console.log(response.error.metadata.payment_id);
        alert("Oops! Payment Failed!");
    });

    rzp.open();
}
