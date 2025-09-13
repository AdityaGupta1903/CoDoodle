import { useState, useRef } from "react";
import { Stage, Layer, Rect, Circle, Arrow } from "react-konva";


export default function DrawingBoard() {
    const [shapes, setShapes] = useState<any>([]);
    const [isDrawing, setIsDrawing] = useState<any>(false);
    const [tool, setTool] = useState<any>("rect"); // "rect" | "circle"
    const [currentShapeId, setCurrentShapeId] = useState<any>(null);
    const stageRef = useRef<any>(null);

    const handleMouseDown = (e: any) => {
        setIsDrawing(true);
        const stage = stageRef.current;
        const pos = stage.getPointerPosition();

        const id = Date.now().toString();
        setCurrentShapeId(id);

        if (tool === "rect") {
            setShapes((prev: any) => [
                ...prev,
                { id, type: "rect", x: pos.x, y: pos.y, width: 0, height: 0 },
            ]);
        } else if (tool === "circle") {
            setShapes((prev: any) => [
                ...prev,
                { id, type: "circle", x: pos.x, y: pos.y, radius: 0 },
            ]);
        }
    };

    const handleMouseMove = () => {
        if (!isDrawing) return;
        const stage = stageRef.current;
        const pos = stage && stage.getPointerPosition();

        setShapes((prev: any[]) =>
            prev.map((shape: { id: any; type: string; x: number; y: number; }) => {
                if (shape.id !== currentShapeId) return shape;

                if (shape.type === "rect") {
                    return {
                        ...shape,
                        width: pos.x - shape.x,
                        height: pos.y - shape.y,
                    };
                } else if (shape.type === "circle") {
                    const radius = Math.sqrt(
                        Math.pow(pos.x - shape.x, 2) + Math.pow(pos.y - shape.y, 2)
                    );
                    return { ...shape, radius };
                }
                return shape;
            })
        );
    };

    const handleMouseUp = () => {
        setIsDrawing(false);
        setCurrentShapeId(null);
    };

    return (
        <div>
            {/* Simple toolbar */}
            <div className="toolbar" style={{ marginBottom: "10px" }}>
                <button onClick={() => setTool("rect")}>Rectangle</button>
                <button onClick={() => setTool("circle")}>Circle</button>
                <button onClick={() => setTool("arrow")}>Arrow</button>
            </div>

            {/* Canvas */}
            <Stage
                width={window.innerWidth}
                height={window.innerHeight}
                ref={stageRef}
                onMouseDown={handleMouseDown}
                onMouseMove={handleMouseMove}
                onMouseUp={handleMouseUp}
                style={{ border: "1px solid #ccc" }}
            >
                <Layer>
                    {shapes.map((shape: any) =>
                        shape.type === "rect" ? (
                            <Rect
                                key={shape.id}
                                {...shape}
                                stroke="black"
                                strokeWidth={2}
                                dash={[4, 4]}
                            />
                        ) : (
                            <Circle
                                key={shape.id}
                                {...shape}
                                stroke="blue"
                                strokeWidth={2}
                            />
                        )
                    )}
                </Layer>
            </Stage>
        </div>
    );
}
