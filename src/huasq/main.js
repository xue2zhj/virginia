var WIDTH=600, HEIGHT=600,canvas, context,foodinfo,homeinfo,antnum,infolostspeed=0.01,ants;

function init(){
	canvas=document.getElementById("canvas");
	canvas.width=WIDTH;
	canvas.height=HEIGHT;
	canvas.style.backgroundColor="white";

	context=canvas.getContext("2d");
	context.lineWidth=3;
	context.strokeStyle="black";
	context.fillStyle="white";
	start();
}

function start(){
	var i,j;
	foodinfo=[];
	homeinfo=[];
	for(i=0;i<WIDTH/3+1;i++){
		foodinfo.push(new Array());
		homeinfo.push(new Array());
		for(j=0;j<HEIGHT/3+1;j++){
			foodinfo[i].push(0);
			homeinfo[i].push(0);
		}
	}
	num=Math.ceil(Math.random()*50);
	num=num<30?30:num;
	ants=[];
	for(var i=0;i<num;i++){		
		ants.push(new ant());
		ants[i].draw(context);
	}	
	setTimeout(run, 50);
}

function run(){
	context.clearRect(0,0,WIDTH,HEIGHT);
	context.beginPath();
	context.moveTo(0,0);
	context.lineTo(90,0);
	context.lineTo(0,90);
	context.lineTo(0,0);
	context.fillStyle="rgba(200,200,200,0.8)";
	context.fill();	
	context.closePath();
	
	context.beginPath();
	context.moveTo(WIDTH,HEIGHT);
	context.lineTo(WIDTH-60,HEIGHT);
	context.lineTo(WIDTH,HEIGHT-60);
	context.lineTo(WIDTH,HEIGHT);
	context.fillStyle="rgba(10,10,200,0.8)";
	context.fill();
	context.closePath();	
	for(i=0;i<=WIDTH/3;i++){
		for(j=0;j<=HEIGHT/3;j++){
			foodinfo[i][j]=foodinfo[i][j]>infolostspeed?foodinfo[i][j]-infolostspeed:0;
			homeinfo[i][j]=homeinfo[i][j]>infolostspeed?homeinfo[i][j]-infolostspeed:0;
		}
	}	
	var temp;
	for(var i=0;i<num;i++){
		temp=ants[i].position.slice(0);
		ants[i].updateState();
		if(ants[i].position.length==3){
			alert(ants[i].position.length);
			continue;
		}
		ants[i].draw(context);
	}
	setTimeout(run, 20);
}

function ant(){
	this.position=[];
	this.oldways=[];
	this.hasfood=false;
	this.direction=0;
	this.init();
}

ant.prototype={
		constructor :ant,
		antinfo:2,
		draw:function (context){
			context.beginPath();
			context.moveTo(this.position[0][0],this.position[0][1]);
			if(this.hasfood)
				context.strokeStyle="green";
			else
				context.strokeStyle="red";
			context.lineTo(this.position[1][0],this.position[1][1]);
			context.stroke();	
			context.closePath();
			context.beginPath();
			context.strokeStyle="black";
			context.moveTo(this.position[1][0],this.position[1][1]);			
			context.lineTo(this.position[2][0],this.position[2][1]);
			context.lineTo(this.position[3][0],this.position[3][1]);			
			context.stroke();	
			context.closePath();
			
		},
		isoldway:function(x,y){
			for(var oldway in this.oldways){
				if(oldway[0]==x&&oldway[1]==y)
					return true;
			}
			return false;
		},
		init:function(){
			this.hasfood=false;
			this.direction=Math.floor(Math.random()*8)%8;
			var x=9+Math.floor(Math.random()*70);
			x=x-(x%3);
			var y=9+Math.floor(Math.random()*(80-x));	
			y=y-(y%3);
			this.position.push([x,y]);	
			
			this.oldways.unshift([x,y]);
			if(this.oldways.length>3)
				this.oldways.pop();
			switch(this.direction){
			case 0:
				this.position.push([x,y+3],[x,y+6],[x,y+9]);
				break;
			case 1:
				this.position.push([x-3,y+3],[x-6,y+6],[x-9,y+9]);
				break;
			case 2:
				this.position.push([x-3,y],[x-6,y],[x-9,y]);
				break;
			case 3:
				this.position.push([x-3,y-3],[x-6,y-6],[x-9,y-9]);
				break;
			case 4:
				this.position.push([x,y-3],[x,y-6],[x,y-9]);
				break;
			case 5:
				this.position.push([x+3,y-3],[x+6,y-6],[x+9,y-9]);
				break;
			case 6:
				this.position.push([x+3,y],[x+6,y],[x+9,y]);
				break;
			case 7:
				this.position.push([x+3,y+3],[x+6,y+6],[x+9,y+9]);
				break;
			}
		},
		updateState:function(){		
			var cur=this.position[0],path;
			if(!this.hasfood){
				var maxAddr;
				var last=this.position.pop();
				if(hasfood(cur[0],cur[1])){
					this.hasfood=true;
					this.position.reverse();
					this.position.unshift(last);
					this.direction=(this.direction+4)%8;
					return;
				}
				switch(this.direction){
				case 0:		
				case 1:	
					if(cur[0]+3<=WIDTH&&cur[1]-3>=0){
						if(hasfood(cur[0]+3,cur[1]-3)){this.position.unshift([cur[0]+3,cur[1]-3]);return;}
						else if(hasfood(cur[0]+3,cur[1])){this.position.unshift([cur[0]+3,cur[1]]);return;}
						else if(hasfood(cur[0],cur[1]-3)){this.position.unshift([cur[0],cur[1]-3]);return;}
					}else if(cur[0]+3>WIDTH&&cur[1]-3>=0){
						if(hasfood(cur[0],cur[1]-3)){
							this.position.unshift([cur[0],cur[1]-3]);	
							return;
						}else if(hasfood(cur[0]-3,cur[1]-3)){
							this.position.unshift([cur[0]-3,cur[1]-3]);	
							return;
						}
					}else if(cur[0]+3<=WIDTH&&cur[1]-3<0){
						if(hasfood(cur[0]+3,cur[1])){
							this.position.unshift([cur[0]+3,cur[1]]);	
							return;
						}else if(hasfood(cur[0]+3,cur[1]+3)){
							this.position.unshift([cur[0]+3,cur[1]+3]);	
							return;
						}
					}
					break;
				case 2:		
				case 3:
				case 4:
					if(cur[0]+3<=WIDTH&&cur[1]+3<=HEIGHT){
						if(hasfood(cur[0]+3,cur[1]+3)){this.position.unshift([cur[0]+3,cur[1]+3]);	return;}							
						else if(hasfood(cur[0]+3,cur[1])){this.position.unshift([cur[0]+3,cur[1]]);return;}								
						else if(hasfood(cur[0],cur[1]+3)){this.position.unshift([cur[0],cur[1]+3]);	return;}											
					}else if(cur[0]+3>WIDTH&&cur[1]+3<=HEIGHT){
						if(hasfood(cur[0],cur[1]+3)){
							this.position.unshift([cur[0],cur[1]+3]);	
							return;
						}else if(hasfood(cur[0]-3,cur[1]+3)){
							this.position.unshift([cur[0]-3,cur[1]+3]);	
							return;
						}
					}else if(cur[0]+3<=WIDTH&&cur[1]+3>HEIGHT){
						if(hasfood(cur[0]+3,cur[1])){
							this.position.unshift([cur[0]+3,cur[1]]);	
							return;
						}else if(hasfood(cur[0]+3,cur[1]-3)){
							this.position.unshift([cur[0]+3,cur[1]-3]);	
							return;
						}
					}
					break;
				case 5:
				case 6:
					if(cur[0]-3>=0&&cur[1]+3<=HEIGHT){
						if(hasfood(cur[0]-3,cur[1]+3)){this.position.unshift([cur[0]-3,cur[1]+3]);	return;}							
						else if(hasfood(cur[0]-3,cur[1])){this.position.unshift([cur[0]-3,cur[1]]);return;}								
						else if(hasfood(cur[0],cur[1]+3)){this.position.unshift([cur[0],cur[1]+3]);return;}										
					}else if(cur[0]-3<0&&cur[1]+3<=HEIGHT){
						if(hasfood(cur[0],cur[1]+3)){
							this.position.unshift([cur[0],cur[1]+3]);	
							return;
						}else if(hasfood(cur[0]+3,cur[1]+3)){
							this.position.unshift([cur[0]+3,cur[1]+3]);	
							return;
						}
					}else if(cur[0]-3>=0&&cur[1]+3>HEIGHT){
						if(hasfood(cur[0]-3,cur[1])){
							this.position.unshift([cur[0]-3,cur[1]]);	
							return;
						}else if(hasfood(cur[0]-3,cur[1]-3)){
							this.position.unshift([cur[0]-3,cur[1]-3]);	
							return;
						}
					}
					break;
				case 7:
					if(cur[0]-3>=0&&cur[1]-3>=0){
						if(hasfood(cur[0]-3,cur[1]-3)){this.position.unshift([cur[0]-3,cur[1]-3]);	return;}
						else if(hasfood(cur[0],cur[1]-3)){this.position.unshift([cur[0],cur[1]-3]);	return;}
						else if(hasfood(cur[0]-3,cur[1])){this.position.unshift([cur[0]-3,cur[1]]);	return;}
					}else if(cur[0]-3<0&&cur[1]-3>=0){
						if(hasfood(cur[0],cur[1]-3)){
							this.position.unshift([cur[0],cur[1]-3]);	
							return;
						}else if(hasfood(cur[0]+3,cur[1]-3)){
							this.position.unshift([cur[0]+3,cur[1]-3]);	
							return;
						}
					}else if(cur[0]-3>=0&&cur[1]-3<0){
						if(hasfood(cur[0]-3,cur[1])){
							this.position.unshift([cur[0]-3,cur[1]]);	
							return;
						}else if(hasfood(cur[0]-3,cur[1]+3)){
							this.position.unshift([cur[0]-3,cur[1]+3]);	
							return;
						}
					}
					break;
				}			
				path=getPathInfo(0,cur,this.direction);
				this.position.unshift(path[0]);
				this.direction=path[1];
				if(this.position[0][0]+this.position[0][1]>90&&!hasfood(this.position[0][0],this.position[0][1]))
					homeinfo[this.position[0][0]/3][this.position[0][1]/3]=homeinfo[this.position[0][0]/3][this.position[0][1]/3]+this.antinfo;
			}else{
				
			}
			
		},
	}

function hasfood(x,y){
	if(x+y>=1140)
		return true;
	else
		return false;
}

function getPathInfo(type,cur,diret){
	var infos=[];
	switch(diret){
	case 0:
	case 1:
	case 2:
		break;
	case 3:
		if(cur[1]+3<=HEIGHT){
			infos.push([[cur[0],cur[1]+3],4]);
			if(cur[0]-3>=0)
				infos.push([[cur[0]-3,cur[1]+3],5]);			
			if(cur[0]+3<=WIDTH)
				infos.push([[cur[0]+3,cur[1]+3],3]);	
		}else{
			if(cur[0]+3<=WIDTH)
				infos.push([[cur[0]+3,cur[1]],2]);
			if(cur[1]-3>=0)
				infos.push([[cur[0]+3,cur[1]-3],1]);
		}
		if(infos.length<1){
			if(cur[0]-3>=0)
				infos.push([[cur[0]-3,cur[1]],6]);
			if(cur[1]-3>=0)
				infos.push([[cur[0],cur[1]-3],0]);
		}
		return getMostFoodInfoPos(infos);
	}	
}

function getMostFoodInfoPos(infos){
	var maxfood=foodinfo[infos[0][0][0]][infos[0][0][1]],index=0;	
	for(var i=1;i<poss.length;i++){
		if(foodinfo[infos[i][0][0]][infos[i][0][1]]>maxfood){
			index= i;
		}
	}
	return infos[i];
}