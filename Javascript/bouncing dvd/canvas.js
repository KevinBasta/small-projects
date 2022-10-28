let theCanvas = document.getElementById('myCanvas');
let context = theCanvas.getContext("2d");
let x;
let y; 
let xspeed; 
let yspeed; 


window.onload = function() {
    dvdImg = new Image();
    dvdImg.src = '1.png';
    dvdImg.onload = function(){
        context.drawImage(dvdImg, x, y);
    }

    x = randomInt(theCanvas.width); 
    y = randomInt(theCanvas.height);
    xspeed = 5; 
    yspeed = 5; 

    gameLoop(); 
    
    function gameLoop() {
        update();
        draw();
    
        window.requestAnimationFrame(gameLoop);
    }
    
    function draw() { 
        context.clearRect(0, 0, theCanvas.width, theCanvas.height);
        context.drawImage(dvdImg, x, y);
    }

    function update() {         
        if (x + dvdImg.width >= theCanvas.width) { 
            xspeed = -xspeed; 
            x = theCanvas.width - (dvdImg.width);
        } else if (x <= 0) {
            xspeed = -xspeed;  
            x = 0;
        }

        if (y + dvdImg.height >= theCanvas.height) { 
            yspeed = -yspeed;
            y = theCanvas.height - (dvdImg.height);
        } else if (y <= 0) {
            yspeed = -yspeed;
            y = 0;
        }

        x += (xspeed); 
        y += (yspeed); 
        console.log(x, y);
    }
}




function randomInt(max) {
    return Math.floor(Math.random() * max);
}
  