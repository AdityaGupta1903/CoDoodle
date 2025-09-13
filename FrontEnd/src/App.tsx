import React from 'react';
import { Stage, Layer, Line, Text } from 'react-konva';
import DrawingBoard from './subcomponents/Shapes';

const App = () => {
  const [tool, setTool] = React.useState('pen');
  const [lines, setLines] = React.useState<any>([]);
  const isDrawing = React.useRef(false);

  const handleMouseDown = (e:any) => {
    isDrawing.current = true;
    const pos = e.target.getStage().getPointerPosition();
    setLines([...lines, { tool, points: [pos.x, pos.y] }]);
  };

  const handleMouseMove = (e:any) => {
    // no drawing - skipping
    if (!isDrawing.current) {
      return;
    }
    const stage = e.target.getStage();
    const point = stage.getPointerPosition();
    let lastLine = lines[lines.length - 1];
    // add point
    lastLine.points = lastLine.points.concat([point.x, point.y]);

    // replace last
    lines.splice(lines.length - 1, 1, lastLine);
    setLines(lines.concat());
  };

  const handleMouseUp = () => {
    isDrawing.current = false;
  };

  return (
    // <div>
    //   <select
    //     value={tool}
    //     onChange={(e) => {
    //       setTool(e.target.value);
    //     }}
    //   >
    //     <option value="pen">Pen</option>
    //     <option value="eraser">Eraser</option>
    //   </select>
    //   <Stage
    //     width={window.innerWidth}
    //     height={window.innerHeight}
    //     onMouseDown={handleMouseDown}
    //     onMousemove={handleMouseMove}
    //     onMouseup={handleMouseUp}
    //     onTouchStart={handleMouseDown}
    //     onTouchMove={handleMouseMove}
    //     onTouchEnd={handleMouseUp}
    //   >
    //     <Layer>
    //       <Text text="Just start drawing" x={5} y={30} />
    //       {lines.map((line: { points: number[] | Int8Array<ArrayBufferLike> | Uint8Array<ArrayBufferLike> | Uint8ClampedArray<ArrayBufferLike> | Int16Array<ArrayBufferLike> | Uint16Array<ArrayBufferLike> | Int32Array<ArrayBufferLike> | Uint32Array<ArrayBufferLike> | Float32Array<ArrayBufferLike> | Float64Array<ArrayBufferLike> | undefined; tool: string; }, i: React.Key | null | undefined) => (
    //         <Line
    //           key={i}
    //           // @ts-ignore
    //           points={line.points}
    //           stroke="#df4b26"
    //           strokeWidth={5}
    //           tension={0.5}
    //           lineCap="round"
    //           lineJoin="round"
    //           globalCompositeOperation={
    //             // @ts-ignore
    //             line.tool === 'eraser' ? 'destination-out' : 'source-over'
    //           }
    //         />
    //       ))}
    //     </Layer>
    //   </Stage>
    // </div>
    <DrawingBoard/>
  );
};

export default App;
