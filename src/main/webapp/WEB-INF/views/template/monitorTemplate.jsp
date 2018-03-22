<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<html>
<style>
.axis path,
.axis line{
    fill: none;
    stroke: black;
    shape-rendering: crispEdges;
}

.axis text {
    font-family: sans-serif;
    font-size: 11px;
}
</style>
<body>
<script src="${basePath}/resources/bower_components/jquery/jquery.min.js"></script>
<script src="${basePath}/resources/bower_components/d3js-4.13.0/d3.js"></script>

<svg>
<defs>
	<marker id="arrow" markerWidth="10" markerHeight="10" refx="0" refy="3"
			orient="auto" markerUnits="strokeWidth"> <path
			d="M0,0 L0,6 L9,3 z" fill="#f00" /></marker>
	 </defs>
<line x1="50" y1="50" x2="250" y2="50" stroke="#000"
			stroke-width="5" marker-end="url(#arrow)" />
</svg>
<%-- <svg width="100%" height="100%" version="1.1"
xmlns="http://www.w3.org/2000/svg">

<circle cx="100" cy="50" r="40" stroke="black"
stroke-width="2" fill="red"/>

<rect x="200" y="200" width="300" height="100"
style="fill:rgb(0,0,255);stroke-width:1;
stroke:rgb(0,0,0)"/>
<rect x="20" y="20" width="250" height="250"
style="fill:blue;stroke:pink;stroke-width:5;
fill-opacity:0.1;stroke-opacity:0.9"/>
<rect x="40" y="40" rx="40" ry="20" width="250"
height="100" style="fill:red;stroke:black;
stroke-width:5;opacity:0.5"/>
<ellipse cx="300" cy="150" rx="200" ry="80"
style="fill:rgb(200,100,50);
stroke:rgb(0,0,100);stroke-width:2;opacity:0.3"/>
<line x1="0" y1="0" x2="300" y2="300"
style="stroke:rgb(99,99,99);stroke-width:2"/>
<polygon points="220,100 300,210 170,250 123,234"
style="fill:#cccccc;
stroke:#000000;stroke-width:1;opacity:0.3"/>
<polyline points="0,0 0,20 20,20 20,40 40,40 40,60"
style="fill:white;stroke:red;stroke-width:2"/>
<path d="M153 334
C153 334 151 334 151 334
C151 339 153 344 156 344
C164 344 171 339 171 334
C171 322 164 314 156 314
C142 314 131 322 131 334
C131 350 142 364 156 364
C175 364 191 350 191 334
C191 311 175 294 156 294
C131 294 111 311 111 334
C111 361 131 384 156 384
C186 384 211 361 211 334
C211 300 186 274 156 274"
style="fill:white;stroke:red;stroke-width:2"/>
<defs>
<linearGradient id="orange_red" x1="0%" y1="0%" x2="100%" y2="0%">
<stop offset="0%" style="stop-color:rgb(255,255,0);
stop-opacity:1"/>
<stop offset="100%" style="stop-color:rgb(255,0,0);
stop-opacity:1"/>
</linearGradient>
</defs>
<ellipse cx="200" cy="190" rx="85" ry="55"
style="fill:url(#orange_red)"/>
</svg> --%>

<script type="text/javascript">
$(function(){
	/* var dataSet=[4, 16, 23,100,30, 42];
	var linear = d3.scaleLinear().domain([0,d3.max(dataSet)]).range([0,500]);//线性比例尺
	var xAxis=d3.axisBottom(linear)//.ticks(7);//文字在下的x坐标轴
	var yScale = d3.scaleOrdinal().domain(d3.range(dataSet.length))
    //.rangeRoundBands([0,500]);
	
	d3.select("svg").selectAll("rect")
	.data(dataSet).enter()
	.append("rect").attr("x", function(d,i) { return Math.random()*100 + "px"; })
	.attr("y", function(d,i) { return i*20 + "px"; })
	.attr("width", function(d,i) { return d + "px"; })
	.attr("height","18px").call(xAxis);
	d3.selectAll("rect").attr("fill", function() {
	  return "hsl(" + Math.random() * 360 + ",100%,50%)";
	}); */
	var data = [10, 15, 25, 120, 500, 980, 1200];
    
    // Create yScale
    var yScale = d3.scaleLinear()
                   .domain([10, 1200])
                   .range([0, 500]);
    
    d3.select("svg")
      .selectAll("rect")
      .data(data)
      .enter()
      .append("rect")
        .attr("width", 30)
        .attr("height", function(d) { return yScale(d); })
        .attr("x", function(d, i) { return i * 30; })
        .attr("y", function(d) { return 500 - yScale(d);})
        .style("fill", "blue")
		  .style("stroke", "black")
		  .style("stroke-width", "1px")
		  .style("opacity", .25);
    
    var svgContainer = d3.select("body")
    .append("svg")
      .attr("width", 500)
      .attr("height", 400)
      .style("padding", "20px")
      .style("border", "1px solid");
  
  var xScale = d3.scaleLinear()
      .domain([0, 100])
      .range([0, 500]);
  
  var xAxisTop = d3.axisTop(xScale);
  svgContainer.append("g").call(xAxisTop);
  
  var xAxisBottom = d3.axisBottom(xScale);
  svgContainer.append("g")
  .attr("transform", function() {
    return "translate(" + 0 + "," + 390 + ")"
  }).call(xAxisBottom);
  
  
  // First declare some variables for dimension and margin for the container
  var width = 500;
  var height = 500;
  var margin = 25;
  var axisLength = width - 2 * margin; // Leave margin on both sides left-right or top-bottom
  
  // The main container to house our axes groups
  var svgContainer = d3.select("body")
	.append("svg")
	  .attr("width", width)
	  .attr("height", height)
	  .style("border", "1px solid");
 
  // Renders the X-axis and the vertical grid lines
  function renderXAxis() {
	var xScale = d3.scaleLinear()
	  .domain([0, 100])
	  .range([0, axisLength]);
	
	var xAxis = d3.axisBottom(xScale);
	
	svgContainer.append("g")
        // Give a class name to the x-axis group so that we can target it
		.classed("x-axis", true)
		.attr("transform", function() {
          // Translate the x-axis to the bottom of the container leaving
          // margin on the left and bottom
		  return "translate(" + margin + "," + (height - margin) + ")";
		})
		.call(xAxis);
	
        // For each of the tick component create a line element inside the group
	    // This creates the vertical lines of the grid
        svgContainer.selectAll("g.x-axis g.tick")
	    .append("line")
		.classed("grid-line", true)
         // x1,y1 sets the starting point of the line
         // x2,y2 sets the destination point of the line
         // Since the line is inside the g element and the g element is already
         // translated we just need to set the y2 value
		.attr("x1", 0)
		.attr("y1", 0)
		.attr("x2", 0)
		.attr("y2", -(height - 2 * margin));
  }
  
  // This creates the horizontal lines of the grid
  function renderYAxis() {
	var yScale = d3.scaleLinear()
	  .domain([100, 0])
	  .range([0, axisLength]);
	
	var yAxis = d3.axisLeft(yScale);
	
	svgContainer.append("g")
		.classed("y-axis", true)
		.attr("transform", function() {
		  return "translate(" + margin + "," + margin + ")";
		})
		.call(yAxis);
	
	svgContainer.selectAll("g.y-axis g.tick")
	  .append("line")
		.classed("grid-line", true)
		.attr("x1", 0)
		.attr("y1", 0)
		.attr("x2", axisLength)
		.attr("y2", 0);
  }
  
  renderXAxis();
  renderYAxis();
});
</script>
<div style="width:100px; height:100px; position:absolute; background-color:red"></div>
<div class="shape"></div>
    <script>
      d3.select("div")
        .transition()
        .delay(1000)
        .duration(2000)
        .style("left", "400px")
        .style("background-color", "blue");
      
      d3.select("div.shape")
      // Transition 1
      .transition()
        .delay(1000)
        .duration(2000)
        .style("background-color", "red")
        .style("border-radius", "50%")
      // Transition 2
      .transition()
        .delay(1000)
        .duration(2000)
        .style("background-color", "blue")
        .style("border-radius", "0px")
        
        
 var svgContainer = d3.select("body")
	.append("svg")        
var data = [
	{x: 0, y: 4},
	{x: 1, y: 9},
	{x: 2, y: 6},
	{x: 4, y: 5},
	{x: 6, y: 7},
	{x: 7, y: 3},
	{x: 9, y: 2}
];
      
 
/* var line = d3.line()
    .x(function(d) { return xScale(d.x); })
    .y(function(d) { return yScale(d.y); });
 
// Create a path element and set its d attribute value using the line generator created above	
svgContainer.append("path")
  .attr("d", line(data))
  .attr("fill", "none")
  .attr("stroke", "red") */
    </script>
</body>
</html>
