import "./App.css";
import PaginationAndSorting from "./components/PaginationAndSorting";
import PaginationAndSorting1 from "./components/PaginationAndSorting1";

function App() {
  return (
    <>
      <nav className="bg-blue-500 text-white p-4">
        <h1 className="text-2xl font-bold">Hello, Tailwind CSS!</h1>
      </nav>
      {/* <PaginationAndSorting /> */}
      <PaginationAndSorting1/>
    </>
  );
}

export default App;
